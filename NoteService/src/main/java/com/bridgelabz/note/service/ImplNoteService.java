/******************************************************************************
 *  Purpose: This is class of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is class so there is only definition of
 *  		 method which is declare in interface INoteService
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.bridgelabz.note.configuration.AppConfiguration;
import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.dto.LabelToNoteDTO;
import com.bridgelabz.note.dto.NoteChangesDTO;
import com.bridgelabz.note.entity.Collaborator;
import com.bridgelabz.note.entity.Label;
import com.bridgelabz.note.entity.Note;
import com.bridgelabz.note.entity.User;
import com.bridgelabz.note.exception.custom.NoteException;
import com.bridgelabz.note.repository.ICollaboratorRepository;
import com.bridgelabz.note.repository.INoteRepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Constant;
import com.bridgelabz.note.utility.NoteUtility;
import com.bridgelabz.note.utility.TokenUtility;

@Service
public class ImplNoteService implements INoteService {

	private final static Logger LOG = LoggerFactory.getLogger(ImplNoteService.class);

	@Autowired
	private INoteRepository noteRepository;

	@Autowired
	private ICollaboratorRepository collabRepository;

	@Autowired
	private AppConfiguration config;

	@Autowired
	private NoteUtility utility;

	@Autowired
	private ElasticSearchService eSService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Response add(String userIdToken, NoteDTO addDTO) {
		LOG.info(Constant.SERVICE_ADD);
		if (addDTO.getTitle().isBlank() && addDTO.getDescription().isBlank())
			throw new NoteException(Constant.NOTE_SAVE_ERROR);
		Note note = config.modelMapper().map(addDTO, Note.class);

		User user = findUserById(Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject()));
		if (user == null)
			throw new NoteException(Constant.USER_ID_NOT_FOUND);
		note.setUserId(user.getUid());
		note.setUserFname(user.getFname());
		note.setUserLname(user.getLname());
		note.setUserEmail(user.getEmail());
		note = noteRepository.save(note);
		try {
			eSService.addNote(note);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_SAVE, note);
	}

	@Override
	public Response getNoteProfile(String userIdToken, int noteId) {
		
		User user = findUserById(Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject()));
		Note note = noteRepository.findById(noteId).orElse(null);
		if(note==null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (user==null) {
			throw new NoteException(Constant.USER_ID_NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("userEmailToken", TokenUtility.buildToken(user.getEmail()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange("http://user-service/getprofile", HttpMethod.GET, entity, Response.class);
	
		return responseEntity.getBody();
	}

	@Override
	public Response get(String userIdToken) {
		LOG.info(Constant.SERVICE_GET);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());

//		if (findUserById(key) == null)
//			throw new NoteException(Constant.USER_ID_NOT_FOUND);
		List<Note> notes = noteRepository.findAll().stream().filter(i -> i.getUserId() == key)
				.collect(Collectors.toList());
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DETAIL, notes);
	}

	@Override
	public Response getByName(String userId) {
		LOG.info(Constant.SERVICE_GET_BY_NAME);
		List<Note> sorted = noteRepository.findAll().stream()
				.filter(i -> i.getUserId() == Integer.parseInt(TokenUtility.parseToken(userId).getSubject()))
				.sorted(Comparator.comparing(Note::getTitle)).parallel().collect(Collectors.toList());
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DETAIL, sorted);
	}

	@Override
	public Response getByDate(String userId) {
		LOG.info(Constant.SERVICE_GET_BY_DATE);
		List<Note> sorted = noteRepository.findAll().stream()
				.filter(i -> i.getUserId() == Integer.parseInt(TokenUtility.parseToken(userId).getSubject()))
				.sorted(Comparator.comparing(Note::getCreatedDate).reversed()).parallel().collect(Collectors.toList());
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DETAIL, sorted);
	}

	@Override
	public Response getByFilter(String userId, boolean pin, boolean archive, boolean trash) {
		LOG.info(Constant.SERVICE_GET_BY_FILTER);
		int key = Integer.parseInt(TokenUtility.parseToken(userId).getSubject());

		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DETAIL,
				noteRepository.findAll().stream()
						.filter(i -> i.getUserId() == key && i.isArchive() == archive
								&& i.isTrash() == trash)
						.sorted(Comparator.comparing(Note::getCreatedDate).reversed()).parallel()
						.collect(Collectors.toList()));
	}

	@Override
	public Response update(String userIdToken, NoteChangesDTO updateDTO) {
		LOG.info(Constant.SERVICE_UPDATE);
		if ((updateDTO.getTitle().isBlank() && updateDTO.getDescription().isBlank()))
			throw new NoteException(Constant.NOTE_UPDATE_ERROR);
		Note note = noteRepository.findById(updateDTO.getNoteId()).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		User user = findUserById(Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject()));
		if (user == null)
			throw new NoteException(Constant.USER_ID_NOT_FOUND);

		note = utility.setValueFromDTOToNoteModel(user.getUid(), updateDTO, note);
		List<Label> labelsDb = note.getLabels();
		List<Label> labelsNote = updateDTO.getLabels();

		labelsNote.removeIf(p -> {
			return labelsDb.stream().anyMatch(x -> (p.getLabelId() == x.getLabelId()));
		});

		// labelsNote.addAll(labelsNoteAfterRemove);

		note.getLabels().addAll(labelsNote);
		note = noteRepository.save(note);
		try {
			eSService.updateNote(note);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, note);
	}

	@Override
	public Response deletePermenently(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_DELETE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		}
		if (!note.isTrash()) {
			throw new NoteException(Constant.NOT_CANNOT_DELETE);
		}
		noteRepository.delete(note);
		try {
			eSService.deleteNoteDocument(note.getNoteId().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DELETE, true);
	}

	@Override
	public Response pin(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_PIN);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (note.isPin()) {
			note.setPin(false);
		} else {
			note.setPin(true);
			note.setArchive(false);
			note.setTrash(false);
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note));

	}

	@Override
	public Response archive(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_ARCHIVE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (note.isArchive()) {
			note.setArchive(false);

		} else {
			note.setArchive(true);
			note.setPin(false);
			note.setTrash(false);
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note));
	}

	@Override
	public Response archivePin(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_ARCHIVE_PIN);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (note.isArchive()) {
			note.setPin(true);
			note.setArchive(false);
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note));

	}

	@Override
	public Response trash(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_TRASH);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());

		Optional<Note> note = noteRepository.findByNoteIdAndUserId(noteId, key);
		if (!note.isPresent()) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (note.get().isTrash()) {
			note.get().setTrash(false);
		} else {
			note.get().setTrash(true);
			note.get().setPin(false);
			note.get().setArchive(false);
		}
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note.get()));
	}

	@Override
	public Response addLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO) {
		LOG.info(Constant.SERVICE_ADD_LABEL_TO_NOTE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());

		Note note = noteRepository.findByNoteIdAndUserId(labelToNoteDTO.getNoteId(), key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}

		List<Label> labelsDb = note.getLabels();
		List<Label> labelsNote = labelToNoteDTO.getLabels();

		labelsNote.removeIf(p -> {
			return labelsDb.stream().anyMatch(x -> (p.getLabelId() == x.getLabelId()));
		});

		note.getLabels().addAll(labelsNote);
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_ADD_LABEL_TO_NOTE, noteRepository.save(note));
	}

	@Override
	public Response updateLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO) {
		LOG.info(Constant.SERVICE_UPDATE_LABEL_TO_NOTE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(labelToNoteDTO.getNoteId(), key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}

		note.setLabels(labelToNoteDTO.getLabels());
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_ADD_LABEL_TO_NOTE, noteRepository.save(note));
	}

	@Override
	public Response removeLabelFromNote(String userIdToken, LabelToNoteDTO labelToNoteDTO) {
		LOG.info(Constant.SERVICE_REMOVE_LABEL_FROM_NOTE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());

		Note note = noteRepository.findByNoteIdAndUserId(labelToNoteDTO.getNoteId(), key).orElse(null);
		if (note == null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}

		List<Label> labelsDb = note.getLabels();
		List<Label> labelsNote = labelToNoteDTO.getLabels();

		labelsDb.removeIf(p -> {
			return labelsNote.stream().anyMatch(x -> (p.getLabelId() == x.getLabelId()));
		});

		note.setLabels(labelsDb);

		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_REMOVE_LABEL_TO_NOTE, noteRepository.save(note));
	}

	@Override
	public Response addReminder(String userIdToken, int noteId, Date reminder) {
		LOG.info(Constant.SERVICE_ADD_REMINDER);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		if (note.getReminder() != null)
			throw new NoteException(Constant.REMINDER_ALREADY_PRESENT);

		if (reminder.before(new Date()))
			throw new NoteException(Constant.CANNOT_SET_DATE_BEFORE_TODAY);

		note.setReminder(reminder);
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_ADD_REMINDER, noteRepository.save(note));
	}

	@Override
	public Response updateReminder(String userIdToken, int noteId, Date reminder) {
		LOG.info(Constant.SERVICE_UPDATE_REMINDER);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		if (reminder.before(new Date()))
			throw new NoteException(Constant.CANNOT_SET_DATE_BEFORE_TODAY);

		note.setReminder(reminder);
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE_REMINDER, noteRepository.save(note));
	}

	@Override
	public Response removeReminder(String userIdToken, int noteId) {
		LOG.info(Constant.SERVICE_REMOVE_REMINDER);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		note.setReminder(null);
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_REMOVE_REMINDER, noteRepository.save(note));
	}

	@Override
	public Response updateColor(String userIdToken, int noteId, String color) {
		LOG.info(Constant.SERVICE_UPDATE_NOTE_COLOR);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		note.setColor(color);
		return new Response(200, Constant.NOTE_UPDATE_NOTE_COLOR, noteRepository.save(note));
	}

	@Override
	public Response uploadPhoto(String userIdToken, int noteId, MultipartFile[] files) {
		LOG.info(Constant.SERVICE_UPLOAD_PHOTO);

		return new Response(Constant.HTTP_STATUS_OK, "Successfully uploaded files ", savePhoto(files));
	}

	@Override
	public String savePhoto(MultipartFile[] files) {
		StringBuilder fileDownloadUri = new StringBuilder();

		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			Path pathAndFileName = Paths.get(Constant.UPLOAD_PATH, file.getOriginalFilename());
			System.out.println(pathAndFileName);
			fileDownloadUri.append(ServletUriComponentsBuilder.fromCurrentContextPath().path(Constant.UPLOAD_PATH + "/")
					.path(fileName).toUriString() + " ");
			try {
				Files.write(pathAndFileName, file.getBytes());
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return fileDownloadUri.toString();
	}

	@Override
	public Response addCollabToNote(String userIdToken, String collabEmail, int noteId) {
		LOG.info(Constant.SERVICE_COLLAB_ADD);
		Note note = noteRepository
				.findByNoteIdAndUserId(noteId, Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject()))
				.orElse(null);

		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);

		User user = findUserByEmail(collabEmail);

		if (user == null) {
			throw new NoteException(Constant.COLLAB_EMAIL_NOT_PRESENT);
		}

		if (note.getCollaborators().stream().anyMatch(i -> i.getUserEmail().equals(collabEmail))) {
			throw new NoteException(Constant.COLLAB_EMAIL_ALREADY_ADDED);
		}

		Collaborator collab = collabRepository.findByUserEmail(collabEmail).orElse(null);

		if (collab == null) {
			Collaborator collab2 = new Collaborator();
			collab2.setUserEmail(collabEmail);
			collab2.setUserFname(user.getFname());
			collab2.setUserLname(user.getLname());
			collab2 = collabRepository.save(collab2);
			note.getCollaborators().add(collab2);
		} else {
			note.getCollaborators().add(collab);
		}

		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note));
	}

	@Override
	public Response getCollabProfile(String userIdToken, int noteId, int collabId) {
		User user = findUserById(Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject()));
		Note note = noteRepository.findById(noteId).orElse(null);
		if(note==null) {
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		}
		if (user==null) {
			throw new NoteException(Constant.USER_ID_NOT_FOUND);
		}
		if(!note.getCollaborators().stream().anyMatch(i->i.getCollabId()==collabId)) {
			throw new NoteException(Constant.COLLAB_NOT_PRESENT_WITH_ID);
		}
		Collaborator collab = note.getCollaborators().stream().filter(i->i.getCollabId()==collabId).findFirst().get();
		System.out.println(collab.getUserEmail());
		HttpHeaders headers = new HttpHeaders();
		headers.set("userEmailToken", TokenUtility.buildToken(collab.getUserEmail()));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Response> responseEntity = restTemplate.exchange("http://user-service/getprofile", HttpMethod.GET, entity, Response.class);
	
		return responseEntity.getBody();
	}

	@Override
	public Response removeCollabToNote(String userIdToken, String collabEmail, int noteId) {
		LOG.info(Constant.SERVICE_COLLAB_REMOVE);
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		Note note = noteRepository.findByNoteIdAndUserId(noteId, key).orElse(null);
		if (note == null)
			throw new NoteException(Constant.NOTE_ID_NOT_FOUND);
		Collaborator collab = collabRepository.findByUserEmail(collabEmail).orElse(null);
		if (collab == null)
			throw new NoteException(Constant.COLLAB_NOT_PRESENT_WITH_ID);

		note.getCollaborators().remove(collab);
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_UPDATE, noteRepository.save(note));
	}

	@Override
	public User findUserByEmail(String email) {
		User user = restTemplate.getForObject(
				"http://user-service/finduser?userEmailToken=" + TokenUtility.buildToken(email), User.class);
		return user;
	}

	@Override
	public User findUserById(int id) {
		User user = restTemplate.getForObject(
				"http://user-service/finduserbyid?userIdToken=" + TokenUtility.buildToken(String.valueOf(id)),
				User.class);
		return user;
	}

	@Override
	public Response fallback() {
		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_HYSTIX_FALLBACK_RESPONSE, null);
	}

	@Override
	public Response findNoteByTitleOrDescription(String userId, String key) {
		Note note = noteRepository.findById(Integer.parseInt(TokenUtility.parseToken(userId).getSubject()))
				.orElse(null);
		if (note == null) {
			throw new NoteException(Constant.USER_ID_NOT_FOUND);
		}
		try {
			return new Response(Constant.HTTP_STATUS_OK, Constant.ESRESPONSE, eSService.searchByNoteTitle(key));
		} catch (IOException e) {
			throw new NoteException(e.toString());
		}
	}

	@Override
	public Response getByFilterPin(String userId) {
		int key = Integer.parseInt(TokenUtility.parseToken(userId).getSubject());

		return new Response(Constant.HTTP_STATUS_OK, Constant.NOTE_DETAIL,
				noteRepository.findAll().stream()
						.filter(i -> i.getUserId() == key && i.isPin() == true)
						.sorted(Comparator.comparing(Note::getCreatedDate).reversed()).parallel()
						.collect(Collectors.toList()));
	}

}

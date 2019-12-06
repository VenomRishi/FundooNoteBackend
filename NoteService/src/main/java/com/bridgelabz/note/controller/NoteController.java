/******************************************************************************
 *  Purpose: Class is implemented for handling the request coming from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.dto.LabelToNoteDTO;
import com.bridgelabz.note.dto.NoteChangesDTO;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.service.INoteService;
import com.bridgelabz.note.utility.Constant;

@RestController
@RequestMapping("/user/note")
@CrossOrigin
public class NoteController implements INoteController {

	private Logger LOG = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	private INoteService service;

	@Override
	@PostMapping("/add")
	public ResponseEntity<Response> add(@RequestHeader(name = "userId") String userIdToken,
			@RequestBody NoteDTO addDTO) {
		LOG.info(Constant.CONTROLLER_ADD);
		return new ResponseEntity<Response>(service.add(userIdToken, addDTO), HttpStatus.OK);
	}

	@Override
	@PutMapping("/get")
	public ResponseEntity<Response> get(@RequestHeader(name = "userId") String userId) {
		LOG.info(Constant.CONTROLLER_GET);
		return new ResponseEntity<Response>(service.get(userId), HttpStatus.OK);
	}

	@Override
	@GetMapping("/getbyname")
	public ResponseEntity<Response> getByName(@RequestHeader(name = "userId") String userId) {
		LOG.info(Constant.CONTROLLER_GET_BY_NAME);
		return new ResponseEntity<Response>(service.getByName(userId), HttpStatus.OK);
	}

	@Override
	@GetMapping("/getbydate")
	public ResponseEntity<Response> getByDate(@RequestHeader(name = "userId") String userId) {
		LOG.info(Constant.CONTROLLER_GET_BY_DATE);
		return new ResponseEntity<Response>(service.getByDate(userId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/getbyfilter")
	public ResponseEntity<Response> getByFilter(@RequestHeader(name = "userId") String userId,
			@RequestParam(name = "pin") boolean pin, @RequestParam(name = "archive") boolean archive,
			@RequestParam(name = "trash") boolean trash) {
		LOG.info(Constant.CONTROLLER_GET_BY_FILTER);
		return new ResponseEntity<Response>(service.getByFilter(userId, pin, archive, trash), HttpStatus.OK);
	}

	@Override
	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestHeader String userIdToken, @RequestBody NoteChangesDTO updateDTO) {
		LOG.info(Constant.CONTROLLER_UPDATE);
		return new ResponseEntity<Response>(service.update(userIdToken, updateDTO), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestHeader(name = "userId") String userIdToken,
			@RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_DELETE);
		return new ResponseEntity<Response>(service.deletePermenently(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/pin")
	public ResponseEntity<Response> pin(@RequestHeader(name = "userId") String userIdToken,
			@RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_PIN);
		return new ResponseEntity<Response>(service.pin(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/archive")
	public ResponseEntity<Response> archive(@RequestHeader(name = "userId") String userIdToken, @RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_ARCHIVE);
		return new ResponseEntity<Response>(service.archive(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/archivepin")
	public ResponseEntity<Response> archivePin(@RequestHeader(name = "userId") String userIdToken, @RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_ARCHIVE_PIN);
		return new ResponseEntity<Response>(service.archive(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/trash")
	public ResponseEntity<Response> trash(@RequestHeader(name = "userId") String userIdToken,
			@RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_TRASH);
		return new ResponseEntity<Response>(service.trash(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/update/addlabeltonote")
	public ResponseEntity<Response> addLabelToNote(@RequestHeader String userIdToken,
			@RequestBody LabelToNoteDTO labelToNoteDTO) {
		LOG.info(Constant.CONTROLLER_ADD_LABEL_TO_NOTE);
		return new ResponseEntity<Response>(service.addLabelToNote(userIdToken, labelToNoteDTO), HttpStatus.OK);
	}
	
	@Override
	@PutMapping("/update/updatelabeltonote")
	public ResponseEntity<Response> updateLabelToNote(@RequestHeader String userIdToken, @RequestBody LabelToNoteDTO labelToNoteDTO){
		LOG.info(Constant.CONTROLLER_UPDATE_LABEL_TO_NOTE);
		return new ResponseEntity<Response>(service.updateLabelToNote(userIdToken, labelToNoteDTO), HttpStatus.OK);
	}

	@Override
	@PutMapping("/update/removelabelfromnote")
	public ResponseEntity<Response> removeLabelFromNote(@RequestHeader String userIdToken,
			@RequestBody LabelToNoteDTO labelToNoteDTO) {
		LOG.info(Constant.CONTROLLER_REMOVE_LABEL_FROM_NOTE);
		return new ResponseEntity<Response>(service.removeLabelFromNote(userIdToken, labelToNoteDTO), HttpStatus.OK);
	}

	@Override
	@PutMapping("update/addreminder")
	public ResponseEntity<Response> addReminder(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "noteId") int noteId, @RequestParam(name = "reminder") Date reminder) {
		LOG.info(Constant.CONTROLLER_ADD_REMINDER);
		return new ResponseEntity<Response>(service.addReminder(userIdToken, noteId, reminder), HttpStatus.OK);
	}

	@Override
	@PutMapping("update/updatereminder")
	public ResponseEntity<Response> updateReminder(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "noteId") int noteId, @RequestParam(name = "reminder") Date reminder) {
		LOG.info(Constant.CONTROLLER_UPDATE_REMINDER);
		LOG.info(reminder.toString());
		return new ResponseEntity<Response>(service.updateReminder(userIdToken, noteId, reminder), HttpStatus.OK);
	}

	@Override
	@PutMapping("update/deletereminder")
	public ResponseEntity<Response> deleteReminder(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_REMOVE_REMINDER);
		return new ResponseEntity<Response>(service.removeReminder(userIdToken, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("update/updatecolor")
	public ResponseEntity<Response> updateColor(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "noteId") int noteId, @RequestParam(name = "color") String color) {
		LOG.info(Constant.CONTROLLER_UPDATE_NOTE_COLOR);
		return new ResponseEntity<Response>(service.updateColor(userIdToken, noteId, color), HttpStatus.OK);
	}

	@Override
	@PutMapping("/upload")
	public ResponseEntity<Response> addPhoto(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam("noteId") int noteId, @RequestParam("files") MultipartFile[] files) {
		LOG.info(Constant.CONTROLLER_UPLOAD_PHOTO);
		return new ResponseEntity<Response>(service.uploadPhoto(userIdToken, noteId, files), HttpStatus.OK);
	}

	@Override
	@PutMapping("/addcollabtonote")
	public ResponseEntity<Response> addCollabToNote(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "collabEmail") String collabEmail, @RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_ADD_COLLAB_TO_NOTE);
		return new ResponseEntity<Response>(service.addCollabToNote(userIdToken, collabEmail, noteId), HttpStatus.OK);
	}

	@Override
	@PutMapping("/removecollabfromnote")
	public ResponseEntity<Response> removeCollabToNote(@RequestHeader(name = "userIdToken") String userIdToken,
			@RequestParam(name = "collabEmail") String collabEmail, @RequestParam(name = "noteId") int noteId) {
		LOG.info(Constant.CONTROLLER_REMOVE_COLLAB_FROM_NOTE);
		return new ResponseEntity<Response>(service.removeCollabToNote(userIdToken, collabEmail, noteId),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/getallusers")
	public ResponseEntity<Response> getAllUsers() {
		LOG.info(Constant.CONTROLLER_GET_ALL_USERS);
		return new ResponseEntity<Response>(service.getAllUsers(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/findnotes")
	public ResponseEntity<Response> findNoteByTitleOrDescription(@RequestHeader(value = "userIdToken") String userId,
			@RequestParam(value = "key") String key) {
		return new ResponseEntity<Response>(service.findNoteByTitleOrDescription(userId, key), HttpStatus.OK);
	}

}

/******************************************************************************
 *  Purpose: This is class of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is class so there is only definition of
 *  		 method which is declare in interface ILabelService
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.note.configuration.AppConfiguration;
import com.bridgelabz.note.dto.LabelDTO;
import com.bridgelabz.note.dto.LabelChangesDTO;
import com.bridgelabz.note.entity.Label;
import com.bridgelabz.note.entity.Note;
import com.bridgelabz.note.exception.custom.LabelException;
import com.bridgelabz.note.repository.ILabelRespository;
import com.bridgelabz.note.repository.INoteRepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Constant;
import com.bridgelabz.note.utility.LabelUtility;
import com.bridgelabz.note.utility.TokenUtility;

@Service
public class ImplLabelService implements ILabelService {

	@Autowired
	private ILabelRespository labelRepository;

	@Autowired
	private INoteRepository noteRepository;

	@Autowired
	private AppConfiguration config;

	@Override
	public Response add(String userIdToken, LabelDTO addLabelDTO) {
		int userId = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		// code for finding label present in database or not
		if (labelRepository.findAll().stream()
				.anyMatch(i -> i.getLabelName().equals(addLabelDTO.getLabelName()) && i.getUserId() == userId))
			throw new LabelException(Constant.LABEL_CANNOT_ADD);
		Label label = config.modelMapper().map(addLabelDTO, Label.class);
		label.setUserId(userId);

		return new Response(Constant.HTTP_STATUS_OK, Constant.LABEL_SAVE, labelRepository.save(label));
	}

	@Override
	public Response get(String userId) {
		int key = Integer.parseInt(TokenUtility.parseToken(userId).getSubject());
		return new Response(Constant.HTTP_STATUS_OK, Constant.LABEL_DETAIL,
				labelRepository.findAll().stream().filter(i -> i.getUserId() == key));
	}

	@Override
	public Response update(String userIdToken, LabelChangesDTO updateLabelDTO) {
		int userId = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		// code for finding label present in database or not
		if (labelRepository.findAll().stream().anyMatch(i -> i.getLabelName().equals(updateLabelDTO.getLabelName())))
			throw new LabelException(Constant.LABEL_CANNOT_UPDATE);
		Label label = labelRepository.findById(updateLabelDTO.getLabelId()).orElse(null);

		if (label == null) {
			throw new LabelException(Constant.ERROR_LABEL_NOT_FOUND);
		}
		label = LabelUtility.mapDTOToLabel(userId, updateLabelDTO, label);

		return new Response(Constant.HTTP_STATUS_OK, Constant.LABEL_UPDATE, labelRepository.save(label));
	}

	@Override
	public Response delete(String userIdToken, String labelId) {
		int key = Integer.parseInt(TokenUtility.parseToken(userIdToken).getSubject());
		int labelIdTemp = Integer.parseInt(labelId);
		Label label = labelRepository.findByUserIdAndLabelId(key, labelIdTemp).orElse(null);
		if (label == null)
			throw new LabelException(Constant.ERROR_LABEL_NOT_FOUND);

		List<Note> notes = noteRepository.findAll().stream()
				.filter(i -> i.getLabels().stream().anyMatch(l -> l.getLabelId() == labelIdTemp))
				.collect(Collectors.toList());
		for (Note note : notes) {
			note.getLabels().remove(label);
			noteRepository.save(note);
		}
		labelRepository.deleteById(labelIdTemp);

		return new Response(Constant.HTTP_STATUS_OK, Constant.LABEL_DELETE, true);
	}

}

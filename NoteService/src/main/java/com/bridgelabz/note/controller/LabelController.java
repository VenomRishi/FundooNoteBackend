/******************************************************************************
 *  Purpose: Class is implemented for handling the request coming from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   26-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.controller;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.dto.LabelDTO;
import com.bridgelabz.note.dto.LabelChangesDTO;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.service.ILabelService;

@RestController
@RequestMapping("/user/label")
@CrossOrigin
public class LabelController implements ILabelController {

	@Autowired
	private ILabelService service;
	
	//private static final Logger LOG=LoggerFactory.getLogger(LabelController.class);

	@Override
	@PostMapping
	public ResponseEntity<Response> add(@RequestHeader(name = "userId") String userIdToken,
			@Valid @RequestBody LabelDTO addLabelDTO) {
		return new ResponseEntity<Response>(service.add(userIdToken, addLabelDTO), HttpStatus.OK);
	}

	@Override
	@PutMapping("/get")
	public ResponseEntity<Response> get(@RequestHeader String userId) {
		return new ResponseEntity<Response>(service.get(userId), HttpStatus.OK);
	}

	@Override
	@PutMapping
	public ResponseEntity<Response> update(@RequestHeader(name = "userId") String userIdToken,
			@RequestBody LabelChangesDTO updateLabelDTO) {
		return new ResponseEntity<Response>(service.update(userIdToken, updateLabelDTO), HttpStatus.OK);
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Response> delete(@RequestHeader(name = "userId") String userIdToken,
			@RequestParam(name = "labelId") String labelId) {
		return new ResponseEntity<Response>(service.delete(userIdToken, labelId), HttpStatus.OK);
	}
}

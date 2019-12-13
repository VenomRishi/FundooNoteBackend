/******************************************************************************
 *  Purpose: Class is implemented for handling the request coming from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.service.ICollaboratorService;
import com.bridgelabz.note.utility.Constant;

@RestController
@RequestMapping("/collab")
@CrossOrigin
public class CollaboratorController implements ICollaboratorController {

	private static final Logger LOG = LoggerFactory.getLogger(CollaboratorController.class);

	@Autowired
	private ICollaboratorService service;

	@Override
	@PostMapping("/add")
	public ResponseEntity<Response> addCollaborator(@RequestHeader(name = "email") String email) {
		LOG.info(Constant.CONTROLLER_COLLAB_ADD);
		return new ResponseEntity<Response>(service.addCollaborator(email), HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/get")
	public ResponseEntity<Response> getCollaborator(@RequestHeader(name="userIdToken") String userIdToken, @RequestParam(name = "collabEmail") String collabEmail) {
		return new ResponseEntity<Response>(service.getCollaborator(userIdToken, collabEmail), HttpStatus.OK);
	}
	
	@Override
	@GetMapping("/getprofile")
	public ResponseEntity<Response> getCollabProfile(@RequestHeader(name ="userIdToken") String userIdToken, @RequestParam(name="collabEmail")String collabEmail) {
		return new ResponseEntity<Response>(service.getCollabProfile(userIdToken, collabEmail), HttpStatus.OK);
	}

	@Override
	@DeleteMapping("/remove")
	public ResponseEntity<Response> removeCollaborator(@RequestHeader(name = "email") String email) {
		LOG.info(Constant.CONTROLLER_COLLAB_REMOVE);
		return new ResponseEntity<Response>(service.removeCollaborator(email), HttpStatus.OK);
	}

}

/******************************************************************************
  *  Purpose: Interface is used to keep declaration of CollaboratorController 
 *  		 implemented class for handling the request coming from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.controller;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.note.response.Response;

public interface ICollaboratorController {

	/**
	 * Purpose: this method is used to add collaborator for the note
	 * 
	 * @param email this can specify which email should add to note which can become
	 *              collaborator
	 * 
	 * @return returns proper response entity of showing proper response to the user
	 */
	ResponseEntity<Response> addCollaborator(String email);

	/**
	 * Purpose: this method is used to remove collaborator for the note
	 * 
	 * @param email this can specify which email should add to note which can become
	 *              collaborator
	 * 
	 * @return returns proper response entity of showing proper response to the user
	 */
	ResponseEntity<Response> removeCollaborator(String email);
}

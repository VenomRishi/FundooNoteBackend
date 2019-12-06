/******************************************************************************
 *  Purpose: Interface is used to maintain the abstraction of label controller
 *  		 class which has is implemented for handling the request coming 
 *  		 from the user
 *  		 @RestController defines that it will deal with the rest controller
 *  		 @RequestMapping will handle the request
 *  		 in this class handling request related user
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   02-11-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.controller;

import org.springframework.http.ResponseEntity;
import com.bridgelabz.note.dto.LabelDTO;
import com.bridgelabz.note.dto.LabelChangesDTO;
import com.bridgelabz.note.response.Response;

public interface ILabelController {

	/**
	 * Purpose: this method is used handle the request of @PostMapping this method
	 * will going to add new label into database this request accepts the request
	 * from the client and add label into database
	 * 
	 * @param userIdToken user id will specify which label this controller has to
	 *                    show records on the client application
	 * 
	 * @param addLabelDTO this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and store it in the database
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> add(String userIdToken, LabelDTO addLabelDTO);

	/**
	 * Purpose: this method is used handle the request of @GetMapping this method
	 * will going to get all label from database this request accepts the request
	 * from the client and returns all the note records from database
	 * 
	 * @param userId user id will specify which label this controller has to show
	 *               records on the client application
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> get(String userId);

	/**
	 * Purpose: this method is used handle the request of @PutMapping this method
	 * will going to update label into database this request accepts the request
	 * from the client and update label into database
	 * 
	 * @param userIdToken    user id will specify which label this controller has to
	 *                       show records on the client application
	 * 
	 * @param updateLabelDTO this is the response coming from the front end
	 *                       application in the response body and then by using
	 *                       controller the values is going to map into DTO(Data
	 *                       Transfer Object) and which will going to help in
	 *                       service class to get fields and update it in the
	 *                       database
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 * 
	 */
	ResponseEntity<Response> update(String userIdToken, LabelChangesDTO updateLabelDTO);

	/**
	 * Purpose: this method is used handle the request of @DeleteMapping this method
	 * will going to delete label from database this request accepts the request
	 * from the client and delete label from database
	 * 
	 * @param userIdToken user id will specify which label this controller has to
	 *                    show records on the client application
	 * 
	 * @param labelId     this parameter is helps service class to specify which
	 *                    label to delete from database this id comes from client
	 *                    application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> delete(String userIdToken, String labelId);
}

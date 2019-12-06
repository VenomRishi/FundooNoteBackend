/******************************************************************************
 *  Purpose: This is interface of Label Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is interface so there is only declaration 
 *  		 of methods no definition is there
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import com.bridgelabz.note.dto.LabelDTO;
import com.bridgelabz.note.dto.LabelChangesDTO;
import com.bridgelabz.note.response.Response;

public interface ILabelService {

	/**
	 * Purpose: this method is created to add new label entry into the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param addLabelDTO this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and store it in the database
	 * @return once that object is mapped to Note entity then this entity will store
	 *         into database and this will return the object and by the use of
	 *         response class we are generating a proper response to show at client
	 *         side
	 */
	Response add(String userIdToken, LabelDTO addLabelDTO);

	/**
	 * Purpose: this method is created for getting all the label from the database
	 * which will help controller to show the labels into front application
	 * 
	 * @param userId this will uniquely identify the user and helps to fetching the
	 *               records of notes from the database of particular user
	 * @return this method returns a proper response message with object which will
	 *         pass to client side
	 */
	Response get(String userId);

	/**
	 * Purpose: this method is created for updating data which is already stored in
	 * the database and once the new data coming from the user end for the request
	 * of update, by the use of DTO(data transfer object) method will get data and
	 * by the use of repository the new data is updated into database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * 
	 * @param updateLabelDTO this is the response coming from the front end
	 *                       application in the response body and then by using
	 *                       controller the values is going to map into DTO(Data
	 *                       Transfer Object) and which will going to help in
	 *                       service class to get fields and update it in the
	 *                       database
	 * @return once that object is mapped to Note entity then this entity will
	 *         update into database and this will return the object and by the use
	 *         of response class we are generating a proper response to show at
	 *         client side
	 */
	Response update(String userIdToken, LabelChangesDTO updateLabelDTO);

	/**
	 * Purpose: this method is created for deleting the record / label from the
	 * database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param labelId     this parameter coming from the front end application id
	 *                    specify the note id which will helps service to specify
	 *                    which note has to delete and by using this id method will
	 *                    delete note from the database
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response delete(String userIdToken, String labelId);
}

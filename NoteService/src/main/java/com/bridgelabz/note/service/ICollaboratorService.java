/******************************************************************************
 *  Purpose: This is interface of Collaborator Service class in the @Service it
 *  		 handles all the request coming from controller and which is then 
 *  		 process in service class as it is interface so there is only 
 *  		 declaration of methods no definition is there
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import com.bridgelabz.note.entity.User;
import com.bridgelabz.note.response.Response;

public interface ICollaboratorService {
	/**
	 * Purpose: this method is used to add the collaborator as this is service
	 * related part so all the database querying is contains in the service part
	 * only
	 * 
	 * @param email this parameter uniquely identify the email which belongs to user
	 *              and by the help of the email we can add collaborator for that
	 *              email id
	 * @return this returns the Response which is simple POJO(plain old java object)
	 *         which contains status code, message and data if any
	 */
	Response addCollaborator(String email);

	Response getCollaborator(String userIdToken, String collabEmail);
	
	/**
	 * Purpose: this method is used to remove the collaborator as this is service
	 * related part so all the database querying is contains in the service part
	 * only
	 * 
	 * @param email this parameter uniquely identify the email which belongs to user
	 *              and by the help of the email we can add collaborator for that
	 *              email id
	 * @return this returns the Response which is simple POJO(plain old java object)
	 *         which contains status code, message and data if any
	 */
	Response removeCollaborator(String email);

	User findUserById(String userIdToken);

	Response getCollabProfile(String userIdToken, String collabEmail);

	
}

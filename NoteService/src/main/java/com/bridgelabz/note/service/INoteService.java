/******************************************************************************
 *  Purpose: This is interface of Note Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is interface so there is only declaration 
 *  		 of methods no definition is there
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.entity.User;
import com.bridgelabz.note.dto.LabelToNoteDTO;
import com.bridgelabz.note.dto.NoteChangesDTO;
import com.bridgelabz.note.response.Response;

/**
 * @author admin1
 *
 */
public interface INoteService {

	/**
	 * Purpose: this method is created for adding the new note into database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param addDTO      this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and store it in the database
	 * @return once that object is mapped to Note entity then this entity will store
	 *         into database and this will return the object and by the use of
	 *         response class we are generating a proper response to show at client
	 *         side
	 */
	Response add(String userIdToken, NoteDTO addDTO);
	
	Response getNoteProfile(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to show all notes records related a particular
	 * user
	 * 
	 * @param userId this will uniquely identify the user and helps to fetching the
	 *               records of notes from the database of particular user
	 * @return this method returns a proper response message with object which will
	 *         pass to client side
	 */
	Response get(String userId);

	/**
	 * Purpose: this method is used to show all notes records related a particular
	 * user with sorted form by name
	 * 
	 * @param userId this will uniquely identify the user and helps to fetching the
	 *               records of notes from the database of particular user
	 * @return this method returns a proper response message with object which will
	 *         pass to client side
	 */
	Response getByName(String userId);

	/**
	 * Purpose: this method is used to show all notes records related a particular
	 * user with sorted form by created date
	 * 
	 * @param userId this will uniquely identify the user and helps to fetching the
	 *               records of notes from the database of particular user
	 * @return this method returns a proper response message with object which will
	 *         pass to client side
	 */
	Response getByDate(String userId);

	/**key
	 * Purpose: this method is used to update the note record which will going to
	 * come from the user end in service method are going to update into database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param updateDTO   this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and update it in the database
	 * @return once that object is mapped to Note entity then this entity will
	 *         update into database and this will return the object and by the use
	 *         of response class we are generating a proper response to show at
	 *         client side
	 */
	/**
	 * @param userIdToken
	 * @param updateDTO
	 * @return
	 */
	Response update(String userIdToken, NoteChangesDTO updateDTO);

	/**
	 * Purpose: this method is used to delete the note from the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this parameter coming from the front end application id
	 *                    specify the note id which will helps service to specify
	 *                    which note has to delete and by using this id method will
	 *                    delete note from the database
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response deletePermenently(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to pin the note from the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter coming from the front end application id
	 *                    specify the note id which will helps service to specify
	 *                    which note has to pin and by using this id method will pin
	 *                    note in the database
	 * 
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response pin(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to archive the note from the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter coming from the front end application id
	 *                    specify the note id which will helps service to specify
	 *                    which note has to archive and by using this id method will
	 *                    archive note in the database
	 * 
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response archive(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to handle the service related note, note should
	 * be there in archive but user click on pinned in archive note so this note
	 * become pinned and un-archive
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is token by which we can specify which note should be
	 *                    updated
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response archivePin(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to trash the note from the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter coming from the front end application id
	 *                    specify the note id which will helps service to specify
	 *                    which note has to trash and by using this id method will
	 *                    trash note in the database
	 * 
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response trash(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to add labels to existing note which is there in
	 * the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * @param labelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * @return this method returnfallbacks a proper response message with true value which
	 *         will pass to client side
	 */
	Response addLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to update labels to existing note which is there
	 * in the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * @param labelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response updateLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to remove labels from the existing note which is
	 * there in the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * @param labelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response removeLabelFromNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to add reminder to the note which is there in
	 * the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is note id which will help to add reminder to
	 *                    specific note
	 * @param reminder    this parameter contains the date and the time on what day
	 *                    and time reminder should be show on the display
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response addReminder(String userIdToken, int noteId, Date reminder);

	/**
	 * Purpose: this method is used to update reminder to the note which is there in
	 * the database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is note id which will help to add reminder to
	 *                    specific note
	 * @param reminder    this parameter contains the date and the time on what day
	 *                    and time reminder should be show on the display
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response updateReminder(String userIdToken, int noteId, Date reminder);

	/**
	 * Purpose: this method is used to remove reminder from the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is note id which will help to add reminder to
	 *                    specific note
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response removeReminder(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to update note color
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is note id which will help to add reminder to
	 *                    specific note
	 * @param color       this parameter has the colour which helps to add or update
	 *                    the colour of note
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response updateColor(String userIdToken, int noteId, String color);

	/**
	 * Purpose: this method is used to upload the note photo
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is note id which will help to add reminder to
	 *                    specific note
	 * @param files       this input coming from the user end which is having
	 *                    MultipartFile images which will going to store inside the
	 *                    note pictures
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response uploadPhoto(String userIdToken, int noteId, MultipartFile[] files);

	/**
	 * Purpose: this method is used to store the media files into the server
	 * directory and returns the proper link to get the photo back
	 * 
	 * @param files this parameter will accept the media files in the form of
	 *              MultipartFile
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	String savePhoto(MultipartFile[] files);

	/**
	 * Purpose: this method is used to add collaborator to the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param collabEmail this parameter will specify which user to add with note
	 *                    collaborator by using there email id
	 * @param noteId      this is note id which will help to add collaborator to
	 *                    specific note
	 * @return this will returns success message with the links on which user will
	 *         find the media files
	 */
	Response addCollabToNote(String userIdToken, String collabEmail, int noteId);
	
	Response getCollabProfile(String userIdToken, int noteId, int collabId);

	/**
	 * Purpose: this method is used to remove collaborator to the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param collabEmail this parameter will specify which user to remove from note
	 *                    collaborator by using there email id
	 * @param noteId      this is note id which will help to remove collaborator
	 *                    from specific note
	 * @return this method returns a proper response message with true value which
	 *         will pass to client side
	 */
	Response removeCollabToNote(String userIdToken, String collabEmail, int noteId);

	/**
	 * Purpose: this method uses the RestTemplate to get all users from the
	 * different project which is running on different REST API(Representation state
	 * transfer application programming interface) side
	 * 
	 * @return returns the list of users which are enrolled inside UserService
	 *         module
	 */
	Response getAllUsers();

	/**
	 * Purpose: this method is implemented to give proper response even if the Rest
	 * Template will unable to get response from user service this is fault tolerant
	 * method for getAllUsers() where in this method it uses RestTemplate to get
	 * users from user service project
	 * 
	 * @return returns proper failure message with response
	 */
	Response fallback();

	/**
	 * Purpose: this method is used to get all notes by using some sort of filters,
	 * filters can be applied by the use of pinned notes, archived notes and trashed
	 * notes
	 * 
	 * @param userId  this is token by which we can specify which user has the note
	 *                related with
	 * @param pin     this parameter will specify note is pinned or not
	 * @param archive this parameter will specify note is archived or not
	 * @param trash   this parameter will specify note is trashed or not
	 * @return returns the proper notes object back to the request coming from
	 */
	Response getByFilter(String userId, boolean pin, boolean archive, boolean trash);

	/**
	 * Purpose: this method uses ElasticSearch to search notes by using note title
	 * or note description
	 * 
	 * @param userId this is token by which we can specify which user has the note
	 *               related with
	 * @param key    parameter to specify which key to search for
	 * @return returns the proper response of notes object back to user
	 */
	Response findNoteByTitleOrDescription(String userId, String key);

	User findUserByEmail(String email);

	User findUserById(int id);

		

}

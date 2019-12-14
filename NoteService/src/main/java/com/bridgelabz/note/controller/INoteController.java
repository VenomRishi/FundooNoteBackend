/******************************************************************************
 *  Purpose: Interface is used to keep declaration of NoteController implemented
 *  		 class for handling the request coming from the user
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

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.note.dto.LabelToNoteDTO;
import com.bridgelabz.note.dto.NoteDTO;
import com.bridgelabz.note.dto.NoteChangesDTO;
import com.bridgelabz.note.response.Response;

public interface INoteController {

	/**
	 * Purpose: this method is used handle the request of @PostMapping this method
	 * will going to add new note into database this request accepts the request
	 * from the client and add note into database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param addDTO      this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and store it in the database
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> add(String userIdToken, NoteDTO addDTO);

	/**
	 * Purpose: this method is used to serve the API(application programming
	 * interface) request coming from the user and in this method, method getting
	 * profile from user service by using RestTemplate
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this will specify for which note method should get profile
	 *                    in note it maintaining user
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getNoteProfile(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used handle the request of @GetMapping this method
	 * will going to get all note from database this request accepts the request
	 * from the client and returns all the note records from database
	 * 
	 * @param userId user id will specify which note this controller has to show
	 *               records on the client application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> get(String userId);

	/**
	 * Purpose: this method is used handle the request of @GetMapping this method
	 * will going to get all note by name from database this request accepts the
	 * request from the client and returns all the note records from database
	 * 
	 * @param userId user id will specify which note this controller has to show on
	 *               the client application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getByName(String userId);

	/**
	 * Purpose: this method is used handle the request of @GetMapping this method
	 * will going to get all note by date from database this request accepts the
	 * request from the client and returns all the note records from database
	 * 
	 * @param userId user id will specify which note this controller has to show on
	 *               the client application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getByDate(String userId);

	/**
	 * Purpose: this method is used handle the request of @PutMapping this method
	 * will going to update note into database this request accepts the request from
	 * the client and add note into database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param updateDTO   this is the response coming from the front end application
	 *                    in the response body and then by using controller the
	 *                    values is going to map into DTO(Data Transfer Object) and
	 *                    which will going to help in service class to get fields
	 *                    and update it in the database
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> update(String userIdToken, NoteChangesDTO updateDTO);

	/**
	 * Purpose: this method is used handle the request of @DeleteMapping this method
	 * will going to delete note from database this request accepts the request from
	 * the client and add note into database
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter is helps service class to specify which
	 *                    note to delete from database this id comes from client
	 *                    application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> delete(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used handle the request of @PutMapping in this method
	 * is used to pin/un-pin the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param id          this parameter is helps service class to specify which
	 *                    note to delete from database this id comes from client
	 *                    application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> pin(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used handle the request of @PutMapping in this method
	 * is used to archive/un-archive the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter is helps service class to specify which
	 *                    note to update from database this id comes from client
	 *                    application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> archive(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to handle the request of @PutMapping in this
	 * method note should be there in archive but user click on pinned in archive
	 * note so this note become pinned and un-archive
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this is token by which we can specify which note to update
	 *                    from
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> archivePin(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used handle the request of @PutMapping in this method
	 * is used to trash/un-trash the note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * 
	 * @param noteId      this parameter is helps service class to specify which
	 *                    note to delete from database this id comes from client
	 *                    application
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> trash(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to add labels to existing note which is there in
	 * the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * 
	 * @param LabelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> addLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to update labels to existing note which is there
	 * in the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * 
	 * @param LabelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> updateLabelToNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to remove labels from the existing note which is
	 * there in the database
	 * 
	 * @param userIdToken    this is token by which we can specify which user has
	 *                       the note related with
	 * 
	 * @param LabelToNoteDTO this is labelToNoteDTO which has the note id and the
	 *                       list of labels which can be then stored inside the
	 *                       database of existing note
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> removeLabelFromNote(String userIdToken, LabelToNoteDTO labelToNoteDTO);

	/**
	 * Purpose: this method is used to add reminder to note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this note id will specify which note should have to make
	 *                    changes
	 * @param reminder    this is the parameter by which we can set reminder to the
	 *                    note
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> addReminder(String userIdToken, int noteId, Date reminder);

	/**
	 * Purpose: this method is used to update reminder to note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this note id will specify which note should have to make
	 *                    changes
	 * @param reminder    this is the parameter by which we can set reminder to the
	 *                    note
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> updateReminder(String userIdToken, int noteId, Date reminder);

	/**
	 * Purpose: this method is used to remove reminder from note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this note id will specify which note should have to make
	 *                    changes
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> deleteReminder(String userIdToken, int noteId);

	/**
	 * Purpose: this method is used to update the colour of note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this note id will specify which note should have to make
	 *                    changes
	 * @param color       this parameter has the colour which helps to add or update
	 *                    the colour of note
	 * @return thisthis will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> updateColor(String userIdToken, int noteId, String color);

	/**
	 * Purpose: this method is used to upload photo related note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param noteId      this note id will specify which note should have to make
	 *                    changes
	 * @param files       this input coming from the user end which is having
	 *                    MultipartFile images which will going to store inside the
	 *                    note pictures
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> addPhoto(String userIdToken, int noteId, MultipartFile[] files);

	/**
	 * Purpose: this method is used to add collaborator to note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param collabEmail this can specify on which email method has to map notes
	 * 
	 * @param noteId      this can specify the note details
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> addCollabToNote(String userIdToken, String collabEmail, int noteId);

	/**
	 * Purpose: this method is used to serve the API(application programming
	 * interface) request coming from the user and in this method, method getting
	 * profile from user service by using RestTemplate
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *             this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application       note related with
	 * @param noteId      this can specify the note details
	 * @param collabId    this can specify the collaborator details
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getCollabProfile(String userIdToken, int noteId, int collabId);

	/**
	 * Purpose: this method is used to remove collaborator to note
	 * 
	 * @param userIdToken this is token by which we can specify which user has the
	 *                    note related with
	 * @param collabEmail this can specify on which email method has to map notes
	 * 
	 * @param noteId      this can specify the note details
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> removeCollabToNote(String userIdToken, int noteId, String collabEmail);

	/**
	 * Purpose: this method is implemented to give proper response even if the Rest
	 * Template will unable to get response from user service this is fault tolerant
	 * method for getAllUsers() where in this method it uses RestTemplate to get
	 * users from user service project
	 * 
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> fallback();

	/**
	 * Purpose: this method is used to find the notes by some sort of filters like
	 * pinned notes, archived notes, trashed notes will search by this filter and
	 * return the filtered record back to the request comes from the front end
	 * 
	 * @param userIdToken this will specify for which user notes should be searched
	 * @param pin         parameter for filtering the pinned notes
	 * @param archive     parameter for filtering the archived notes
	 * @param trash       parameter for filtering the trashed notes
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getByFilter(String userIdToken, boolean pin, boolean archive, boolean trash);

	/**
	 * Purpose: this method uses ElasticSearch to search element from the
	 * ES(ElasticSearch) is open source search engine for searching anything from
	 * the documents
	 * 
	 * @param userIdToken this will specify for which user notes should be searched
	 * @param key         this will specify what to be searched
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> findNoteByTitleOrDescription(String userIdToken, String key);

	/**
	 * Purpose: this method is used to getting records by filtering notes by the
	 * pinned note
	 * 
	 * @param userId this can specify for which user notes to fetch
	 * @return this will returns ResponseEntity<Response> which will show a proper
	 *         message on the client application
	 */
	ResponseEntity<Response> getByFilterPin(String userId);

}

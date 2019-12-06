/******************************************************************************
 *  Purpose: This class is utility class which map all the value from the 
 *  		 updateDTO to note entity class object
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   26-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.utility;


import org.springframework.stereotype.Component;

import com.bridgelabz.note.dto.NoteChangesDTO;
import com.bridgelabz.note.entity.Note;

@Component
public class NoteUtility {

	/**
	 * Purpose: this method is used to set the value which coming from the updateDTO
	 * and for each field method will going to map all the fields into the Note
	 * entity class
	 * 
	 * @param updateDTO this is response coming from the user end which is passed
	 *                  from the controller to the service layer
	 * @param note      this is entity class which will help service layer to save
	 *                  the entity into database in this method, method is setting
	 *                  all the value from updateDTO to note entity
	 * @return returns the note object back to program
	 */
	public Note setValueFromDTOToNoteModel(int key, NoteChangesDTO updateDTO, Note note) {
		note.setNoteId(updateDTO.getNoteId());
		note.setTitle(updateDTO.getTitle());
		note.setDescription(updateDTO.getDescription());
		note.setReminder(updateDTO.getReminder());
		note.setColor(updateDTO.getColor());
		note.setPin(updateDTO.isPin());
		note.setArchive(updateDTO.isArchive());
		note.setUserId(key);
		return note;
	}

}

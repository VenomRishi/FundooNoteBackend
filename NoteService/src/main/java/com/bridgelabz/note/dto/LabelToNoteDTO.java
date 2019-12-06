/******************************************************************************
 *  Purpose: This class is simple DTO(Data Transfer Object) class which can map
 *  		 the values to this class on the basis of the user response which
 *  		 is catch by @RequestBody annotation and then mapping into this 
 *  		 class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   01-10-2019
 *
 ******************************************************************************/


package com.bridgelabz.note.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.bridgelabz.note.entity.Label;
import com.bridgelabz.note.utility.Constant;

import lombok.Data;

@Data
public class LabelToNoteDTO {

	@NotNull(message = Constant.VALIDATE_NOTE_ID)
	private int noteId;

	private List<Label> labels;

}

/******************************************************************************
 *  Purpose: This class is simple DTO(Data Transfer Object) class which can map
 *  		 the values to this class on the basis of the user response which
 *  		 is catch by @RequestBody annotation and then mapping into this 
 *  		 class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.dto;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.bridgelabz.note.entity.Label;
import com.bridgelabz.note.utility.Constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteChangesDTO {

	@NotEmpty(message = Constant.VALIDATE_NOTE_ID)
	private int noteId;
	private String title;
	private String description;
	private Date reminder;
	@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})|([0]{0})$", message = "Invalid color")
	private String color;
	private boolean isPin;
	private boolean isArchive;
	private List<Label> labels;

}

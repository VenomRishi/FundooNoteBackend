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

import com.bridgelabz.note.entity.Collaborator;
import com.bridgelabz.note.entity.Label;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {

	
	private String title;
	private String description;
	private Date reminder;
	private String color;
	private boolean isPin;
	private boolean isArchive;
	private List<Label> labels;
	private List<Collaborator> collabs;

}

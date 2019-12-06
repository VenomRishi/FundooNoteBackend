/******************************************************************************
 *  Purpose: This class is simple DTO(Data Transfer Object) class which can map
 *  		 the values to this class on the basis of the user response which
 *  		 is catch by @RequestBody annotation and then mapping into this 
 *  		 class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.dto;

import javax.validation.constraints.NotEmpty;

import com.bridgelabz.note.utility.Constant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelChangesDTO {
	@NotEmpty(message = Constant.VALIDATE_LABEL_ID)
	private int labelId;
	@NotEmpty(message = Constant.VALIDATE_LABEL)
	private String labelName;
}

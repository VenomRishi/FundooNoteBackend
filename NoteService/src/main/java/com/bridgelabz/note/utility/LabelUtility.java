/******************************************************************************
 *  Purpose: This class is utility class which has the business logic code
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.utility;

import com.bridgelabz.note.dto.LabelChangesDTO;
import com.bridgelabz.note.entity.Label;

public class LabelUtility {
	/**
	 * Purpose: this method is created for mapping the DTO(data transfer object)
	 * into the label object
	 * 
	 * @param updateLabelDTO this is DTO(data transfer object) has the value which
	 *                       has comes from front end application and by the
	 *                       controller DTO(data transfer object) has filled with
	 *                       values
	 * @param label          this is entity object in which method will going to
	 *                       mapped the values from updateLabelDTO to label entity
	 *                       object
	 * @return return entity object back to the method who has called this method
	 *         once all the values has mapped to the label entity object
	 */
	public static Label mapDTOToLabel(int key, LabelChangesDTO updateLabelDTO, Label label) {
		label.setLabelId(updateLabelDTO.getLabelId());
		label.setLabelName(updateLabelDTO.getLabelName());
		label.setUserId(key);

		return label;
	}
}

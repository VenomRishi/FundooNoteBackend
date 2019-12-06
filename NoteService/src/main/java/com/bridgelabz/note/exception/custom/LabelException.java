/******************************************************************************
 *  Purpose: This class is created for catching label level exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.exception.custom;

public class LabelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LabelException(String message) {
		super(message);
	}
}

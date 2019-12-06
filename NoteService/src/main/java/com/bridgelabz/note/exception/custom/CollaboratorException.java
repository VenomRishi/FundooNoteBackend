/******************************************************************************
 *  Purpose: This class is created for catching label level exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.exception.custom;

public class CollaboratorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CollaboratorException(String message) {
		super(message);
	}
}

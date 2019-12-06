/******************************************************************************
 *  Purpose: This class is created for catching note level exception
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.exception.custom;

public class NoteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoteException(String message) {
		super(message);
	}
}

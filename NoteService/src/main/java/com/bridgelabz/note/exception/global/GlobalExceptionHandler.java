/******************************************************************************
 *  Purpose: This class is major class for handling all exception which can be
 *  		 throw while the application is running in the server side, as this
 *  		 class is global exception handler it will handle custom exception 
 *  	 	 and built in exception also, this class purpose is to handle 
 *  		 application from crashing even in critical situation and giving
 *  		 proper response if crashing.
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.note.exception.custom.CollaboratorException;
import com.bridgelabz.note.exception.custom.LabelException;
import com.bridgelabz.note.exception.custom.NoteException;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Constant;

@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * Purpose: this method is created for handle the global exception which can
	 * occur while running the application
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_INTERNAL_SERVER_ERROR, ex.getMessage(), null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Purpose: this method is created for handling the note level exception which
	 * can throw from the service class and it will properly handles the exception
	 * and avoid application from crashing
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(NoteException.class)
	public ResponseEntity<Response> noteExceptionHandler(NoteException ex) {
		return new ResponseEntity<>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handling the label level exception which
	 * can throw from the service class and it will properly handles the exception
	 * and avoid application from crashing
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(LabelException.class)
	public ResponseEntity<Response> labelExceptionHandler(LabelException ex) {
		return new ResponseEntity<Response>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * Purpose: this method is created for handling the label level exception which
	 * can throw from the service class and it will properly handles the exception
	 * and avoid application from crashing
	 * 
	 * @param ex this parameter will be exception which will going to help for to
	 *           responds back to client side even if application is throwing
	 *           exception
	 * 
	 * @return ResponseEntity<Response> this is response entity which will give the
	 *         proper response to client side application in this ResponseEntity we
	 *         are going to attach Response which holds status code, message and
	 *         data
	 */
	@ExceptionHandler(CollaboratorException.class)
	public ResponseEntity<Response> collaboratorExceptionHandler(CollaboratorException ex) {
		return new ResponseEntity<Response>(new Response(Constant.HTTP_STATUS_BAD_REQUEST, ex.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
}

/******************************************************************************
 *  Purpose: This is Static Reference Service class which holds all the static
 *  		 references which can be used in application throughout
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.utility;

public class Constant {

	// controller logs
	public final static String CONTROLLER_ADD = "Controller: add note";
	public final static String CONTROLLER_GET = "Controller: get note";
	public final static String CONTROLLER_GET_BY_NAME = "Controller: get note by name";
	public final static String CONTROLLER_GET_BY_DATE = "Controller: get note by date";
	public final static String CONTROLLER_GET_BY_FILTER = "Controller: get note by filter";
	public final static String CONTROLLER_UPDATE = "Controller: update note";
	public final static String CONTROLLER_DELETE = "Controller: update delete";
	public final static String CONTROLLER_PIN = "Controller: pin note";
	public final static String CONTROLLER_ARCHIVE = "Controller: archive note";
	public final static String CONTROLLER_ARCHIVE_PIN = "Controller: archive pin note";
	public final static String CONTROLLER_TRASH = "Controller: trash note";
	public final static String CONTROLLER_ADD_LABEL_TO_NOTE = "Controller: add label to note";
	public final static String CONTROLLER_UPDATE_LABEL_TO_NOTE = "Controller: update label to note";
	public final static String CONTROLLER_REMOVE_LABEL_FROM_NOTE = "Controller: remove label from note";
	public final static String CONTROLLER_ADD_REMINDER = "Controller: add reminder";
	public final static String CONTROLLER_UPDATE_REMINDER = "Controller: update reminder";
	public final static String CONTROLLER_REMOVE_REMINDER = "Controller: remove reminder";
	public final static String CONTROLLER_UPDATE_NOTE_COLOR = "Controller: update color";
	public final static String CONTROLLER_UPLOAD_PHOTO = "Controller: upload photo";
	public final static String CONTROLLER_ADD_COLLAB_TO_NOTE = "Controller: add collaborator to note";
	public final static String CONTROLLER_REMOVE_COLLAB_FROM_NOTE = "Controller: remove collaborator from note";
	public final static String CONTROLLER_COLLAB_ADD = "Controller: add collaborator";
	public final static String CONTROLLER_COLLAB_REMOVE = "Controller: remove collaborator";
	public final static String CONTROLLER_GET_ALL_USERS = "Controller: get users";

	// service log
	public final static String SERVICE_ADD = "Service: add note";
	public final static String SERVICE_GET = "Service: get note";
	public final static String SERVICE_GET_BY_NAME = "Service: get by name note";
	public final static String SERVICE_GET_BY_DATE = "Service: get by date note";
	public final static String SERVICE_GET_BY_FILTER = "Service: get by filter";
	public final static String SERVICE_UPDATE = "Service: update note";
	public final static String SERVICE_DELETE = "Service: delete note";
	public final static String SERVICE_PIN = "Service: pin note";
	public final static String SERVICE_ARCHIVE = "Service: archive note";
	public final static String SERVICE_ARCHIVE_PIN = "Service: archive pin note";
	public final static String SERVICE_TRASH = "Service: trash note";
	public final static String SERVICE_ADD_LABEL_TO_NOTE = "Service: add label to note";
	public final static String SERVICE_UPDATE_LABEL_TO_NOTE = "Service: update label to note";
	public final static String SERVICE_REMOVE_LABEL_FROM_NOTE = "Service: remove label from note";
	public final static String SERVICE_ADD_REMINDER = "Service: add reminder";
	public final static String SERVICE_UPDATE_REMINDER = "Service: update reminder";
	public final static String SERVICE_REMOVE_REMINDER = "Service: delete reminder";
	public final static String SERVICE_UPDATE_NOTE_COLOR = "Service: update note color";
	public final static String SERVICE_UPLOAD_PHOTO = "Service: upload photo";
	public final static String SERVICE_ADD_COLLAB_TO_NOTE = "Service: add collaborator to note";
	public final static String SERVICE_REMOVE_COLLAB_FROM_NOTE = "Service: remove collaborator from note";
	public final static String SERVICE_COLLAB_ADD = "Service: add collaborator";
	public final static String SERVICE_COLLAB_REMOVE = "Service: remove collaborator";

	// custom exception message
	public final static String NOTE_ID_NOT_FOUND = "note id or user id is not matched with the database";
	public static final String VALIDATION_EXCEPTION = "Validation Exception";
	public static final String NOT_CANNOT_DELETE = "note cannot delete because not is not in trash";
	public static final String LABEL_CANNOT_ADD = "Label cannot add due to label name already present in the database";
	public static final String LABEL_CANNOT_UPDATE = "Label cannot update due to label name already present in the database";
	public static final String REMINDER_ALREADY_PRESENT = "reminder for the note already present";
	public static final String CANNOT_SET_DATE_BEFORE_TODAY = "cannot set reminder of before now";
	public static final String COLLABORATOR_ALREADY_PRESENT = "cannot add collaborator because already present";
	public static final String COLLABORATOR_NOT_PRESENT = "cannot remove collaborator because not present";
	public static final String COLLAB_NOT_PRESENT = "No collaborator found with this email";
	public static final String COLLAB_NOT_PRESENT_WITH_ID = "No collaborator found with this email";
	public static final String USER_ID_NOT_FOUND = "No user present with this id";
	public static final String COLLAB_EMAIL_NOT_PRESENT = "Cannot find email";
	public static final String COLLAB_EMAIL_ALREADY_ADDED = "Collaborator already added";

	// http exception
	public final static int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
	public final static int HTTP_STATUS_BAD_REQUEST = 400;
	public final static int HTTP_STATUS_OK = 200;

	// note related
	public final static String NOTE_SAVE = "Note stored successfully";
	public final static String NOTE_DETAIL = "note details";
	public final static String NOTE_UPDATE = "Note updated successfully";
	public final static String NOTE_DELETE = "Note deleted successfully";
	public final static String NOTE_SAVE_ERROR = "Error in adding note";
	public final static String NOTE_UPDATE_ERROR = "Note update error";
	public final static String NOTE_UPDATE_REMINDER = "Note reminder update successfully";
	public final static String NOTE_ADD_REMINDER = "Note reminder added successfully";
	public final static String NOTE_REMOVE_REMINDER = "Note reminder removed successfully";
	public final static String NOTE_ADD_LABEL_TO_NOTE = "Note label added successfully";
	public final static String NOTE_UPDATE_LABEL_TO_NOTE = "Note label updated successfully";
	public final static String NOTE_REMOVE_LABEL_TO_NOTE = "Note label remove successfully";
	public final static String NOTE_UPDATE_NOTE_COLOR = "Note color updated successfully";
	public final static String NOTE_USER_LIST = "Note User list";
	public final static String NOTE_HYSTIX_FALLBACK_RESPONSE = "Hystix response of failure of getAllUsers method";
	public final static String GET_IMAGES_RESPONSE = "get images response";

	// label related
	public final static String LABEL_SAVE = "Label stored successfully";
	public final static String LABEL_DETAIL = "label details";
	public final static String LABEL_UPDATE = "Label updated successfully";
	public final static String LABEL_DELETE = "Label delete successfully";

	// collab related
	public final static String COLLAB_SAVE = "Collaborator stored successfully";
	public final static String COLLAB_DELETE = "Collaborator remove successfully";

	// token related
	public final static String KEY_LOGIN = "loginkey";
	public static final String ERROR_LABEL_NOT_FOUND = "No label found with this id";

	// validation related
	public static final String VALIDATE_LABEL = "Label cannot be blank";
	public static final String VALIDATE_NOTE_ID = "Note id cannot be blank";
	public static final String VALIDATE_NOTE_TITLE = "Note title cannot be blank";
	public static final String VALIDATE_LABEL_ID = "Label id cannot be black";

	// upload image folder path
	public static final String UPLOAD_PATH = System.getProperty("user.dir") + "/uploads";

	// elastic search constants
	public static final String INDEX = "fundoo";
	public static final String TYPE = "note";
	public static final String ESRESPONSE = "Elastic Search Response";
	public static final String ESCONTROLLER_ADD = "Elastic Search Controller: add";
	public final static String UPLOAD_FOLDER = "/home/admin1/Documents/workspace-spring/FundooNoteBackend/UserService/uploads/";;

}

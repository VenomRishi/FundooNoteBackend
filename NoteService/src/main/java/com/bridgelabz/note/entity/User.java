/******************************************************************************
 *  Purpose: This class is created as the model of user and this class is 
 *  		 basically using for de - marshalling the request of RestTemplate
 *  		 which can interact with user service and getting the user object
 *  		 in the form of string for that RestTemplate needs to de - marshall
 *  	 	 it to becoming the string to object
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   01-12-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int uid;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String profile;
	private Date regDate;
	private Date updateDate;
	private boolean active;

}

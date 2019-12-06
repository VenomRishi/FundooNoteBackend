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

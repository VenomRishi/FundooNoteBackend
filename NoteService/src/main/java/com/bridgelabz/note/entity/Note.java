/******************************************************************************
 *  Purpose: Class is implemented for creating the Entity class
 *  		 @Entity will tell the spring framework that this is Entity class
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "note")
@Data
public class Note implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private Integer noteId;

	@Column(name = "note_name")
	private String title;

	@Column(name = "note_description")
	private String description;

	@Column(name = "note_reminder")
	private Date reminder;

	@Column(name = "note_color")
	@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})|([0]{0})$", message = "Invalid color")
	private String color;

	@Column(name = "note_pin", columnDefinition = "boolean default false")
	private boolean isPin;

	@Column(name = "note_archive", columnDefinition = "boolean default false")
	private boolean isArchive;

	@Column(name = "note_trash", columnDefinition = "boolean default false")
	private boolean isTrash;

	@Column(name = "note_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "note_updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@Column(name = "user_id")
	@NotNull
	private int userId;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "note_labels", joinColumns = @JoinColumn(referencedColumnName = "note_id", name = "label_id"), inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "label_id"))
	private List<Label> labels;

	@JsonIgnoreProperties(value = "notes")
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Collaborator> collaborators;

}

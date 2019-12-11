package com.bridgelabz.note.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collaborator")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collaborator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "collab_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int collabId;

	@Column(name = "collab_created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@Column(name = "collab_updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@NotNull
	@Column(name = "collab_user_email")
	private String userEmail;
	
	@Column(name = "collab_user_fname")
	private String userFname;
	
	@Column(name = "collab_user_lname")
	private String userLname;
	
	@Column(name = "collab_image_path")
	private String userImagePath;

	@JsonIgnoreProperties(value = "collaborators")
	@ManyToMany(mappedBy = "collaborators", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Note> notes;
}

/******************************************************************************
 *  Purpose: this interface is repository interface which can give service
 *  		 to use the implementation of JpaRepository this is the class
 *  		 which we are extending in this interface
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.entity.Note;

@Repository
public interface INoteRepository extends JpaRepository<Note, Integer> {
	/**
	 * Purpose: this method is used to find note by using note id and user id
	 * 
	 * @param noteId this parameter helps to find note
	 * @param userId this parameter helps to find note associated with which user
	 * @return returns the Optional<Note>
	 */
	Optional<Note> findByNoteIdAndUserId(Integer noteId, int userId);
}

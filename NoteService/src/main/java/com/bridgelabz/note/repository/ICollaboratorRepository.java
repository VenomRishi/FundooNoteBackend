/******************************************************************************
 *  Purpose: this interface is repository interface which can give service
 *  		 to use the implementation of JpaRepository this is the class
 *  		 which we are extending in this interface
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.entity.Collaborator;

@Repository
public interface ICollaboratorRepository extends JpaRepository<Collaborator, Integer> {
	/**
	 * Purpose: this method is used to find user by using user email
	 * 
	 * @param userEmail this parameter helps to specify the collaborator by using
	 *                  email
	 * @return returns the Optional<Collaborator>
	 */
	Optional<Collaborator> findByUserEmail(String userEmail);
}

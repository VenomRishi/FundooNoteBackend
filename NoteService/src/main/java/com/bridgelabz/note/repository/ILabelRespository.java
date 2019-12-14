/******************************************************************************
 *  Purpose: this interface is repository interface which can give service
 *  		 to use the implementation of JpaRepository this is the class
 *  		 which we are extending in this interface
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   29-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.note.entity.Label;

@Repository
public interface ILabelRespository extends JpaRepository<Label, Integer> {
	/**
	 * Purpose: this method is used to find label by using label id and user id
	 * 
	 * @param userId this parameter helps to find label
	 * @param labelId this parameter helps to find label associated with which user
	 * @return returns the Optional<Label>
	 */
	Optional<Label> findByUserIdAndLabelId(int userId, int labelId);
}

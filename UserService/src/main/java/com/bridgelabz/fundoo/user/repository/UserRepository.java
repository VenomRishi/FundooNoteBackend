/******************************************************************************
 *  Purpose: this interface is repository interface which can give service
 *  		 to use the implementation of JpaRepository this is the class
 *  		 which we are extending in this interface
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   18-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.fundoo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Purpose: this method will find user by email from the JPA Repository
	 * 
	 * @param email this will specify from which email program has to find entity
	 * @return return Optional<User>
	 */
	Optional<User> findByEmail(String email);
}

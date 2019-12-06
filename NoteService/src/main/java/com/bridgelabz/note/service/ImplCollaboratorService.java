/******************************************************************************
 *  Purpose: This is class of User Service class in the @Service it handles
 *  		 all the request coming from controller and which is then process
 *  		 in service class as it is class so there is only definition of
 *  		 method which is declare in interface ICollaboratorService
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   05-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.note.entity.Collaborator;
import com.bridgelabz.note.exception.custom.CollaboratorException;
import com.bridgelabz.note.repository.ICollaboratorRepository;
import com.bridgelabz.note.response.Response;
import com.bridgelabz.note.utility.Constant;

@Service
public class ImplCollaboratorService implements ICollaboratorService {

	private static final Logger LOG = LoggerFactory.getLogger(ImplCollaboratorService.class);

	@Autowired
	private ICollaboratorRepository collabRepository;

	@Override
	public Response addCollaborator(String email) {
		LOG.info(Constant.SERVICE_COLLAB_ADD);
		Collaborator collaborator = collabRepository.findByUserEmail(email).orElse(null);
		if (collaborator != null)
			throw new CollaboratorException(Constant.COLLABORATOR_ALREADY_PRESENT);

		Collaborator collabAdd = new Collaborator();
		collabAdd.setUserEmail(email);

		return new Response(Constant.HTTP_STATUS_OK, Constant.COLLAB_SAVE, collabRepository.save(collabAdd));
	}

	@Override
	public Response removeCollaborator(String email) {
		LOG.info(Constant.SERVICE_COLLAB_REMOVE);
		Collaborator collaborator = collabRepository.findByUserEmail(email).orElse(null);
		if (collaborator == null)
			throw new CollaboratorException(Constant.COLLABORATOR_NOT_PRESENT);
		collabRepository.delete(collaborator);
		return new Response(Constant.HTTP_STATUS_OK, Constant.COLLAB_DELETE, true);
	}

}

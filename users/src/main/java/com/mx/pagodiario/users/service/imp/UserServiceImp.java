package com.mx.pagodiario.users.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mx.pagodiario.users.bo.UserBO;
import com.mx.pagodiario.users.dto.ResponseDTO;
import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.service.UserService;

/**
 * Implementacion de {@link UserService}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Service
@Scope("prototype")
public class UserServiceImp implements UserService {

	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserServiceImp.class);
	
	/**
	 * {@link UserBO}
	 */
	@Autowired
	private UserBO userBO;
	
	@Override
	public ResponseDTO getUsers() {
		
		LOG.info("getUsers");
		
		ResponseDTO responseDTO = userBO.getUsers();
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	@Override
	public ResponseDTO getUserById(String id) {
		
		LOG.info("getUserById");
		
		ResponseDTO responseDTO = userBO.getUserById(id);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	@Override
	public ResponseDTO insertUser(UserDTO userDTO) {
		
		LOG.info("insertUser");
		
		ResponseDTO responseDTO = userBO.insertUser(userDTO);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	@Override
	public ResponseDTO deleteUserById(String id) {
		
		LOG.info("deleteUserById");
		
		ResponseDTO responseDTO = userBO.deleteUserById(id);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

}

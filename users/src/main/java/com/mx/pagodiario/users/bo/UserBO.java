package com.mx.pagodiario.users.bo;

import static com.mx.pagodiario.users.constants.UserConstants.MSG_ERROR;
import static com.mx.pagodiario.users.constants.UserConstants.MSG_OK;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mx.pagodiario.users.assembler.UserAssembler;
import com.mx.pagodiario.users.dto.ResponseDTO;
import com.mx.pagodiario.users.dto.UserDTO;

/**
 * Business Object (Objeto de negocio) para usuario
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Component
@Scope("prototype")
public class UserBO {
	
	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserBO.class);

	/**
	 * {@link UserAssembler}
	 */
	@Autowired
	private UserAssembler userAssembler;
	
	/**
	 * Metodo para obtner todos los usuarios registrados
	 * 
	 * @return {@link ResponseDTO} (object = lista de {@link UserDTO})
	 */
	public ResponseDTO getUsers() {
		
		LOG.info("getUsers");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		List<UserDTO> usersList = userAssembler.getUsers();
		
		responseDTO.setObject(usersList);
		
		if (usersList !=  null && !usersList.isEmpty()) {
			responseDTO.setStatus(true);
			responseDTO.setMsg(MSG_OK);
		} else {
			responseDTO.setStatus(false);
			responseDTO.setMsg(MSG_ERROR);
			LOG.error("No se recuperaron los usuarios");
		}
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para traer usuario por ID de base de datos
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = {@link UserDTO})
	 */
	public ResponseDTO getUserById(String id) {
		
		LOG.info("");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		responseDTO.setStatus(false);
		responseDTO.setMsg(MSG_ERROR);
		
		if(userAssembler.isStrIdValid(id)) {
			
			int intId = userAssembler.getIntFromStr(id);
			
			UserDTO userDTO = userAssembler.getUserById(intId);
			
			if (userDTO != null) {
				
				responseDTO.setStatus(true);
				responseDTO.setMsg(MSG_OK);
				responseDTO.setObject(userDTO);
				
			} else {
				LOG.error("UserDTO null");
			}
			
		} else {
			LOG.error("El ID no es valido");
		}
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para insertar un usuario nuevo
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return {@link ResponseDTO} (object = true si inserto el usuario, false de lo contrario)
	 */
	public ResponseDTO insertUser(UserDTO userDTO) {
		
		LOG.info("insertUser");
		
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setStatus(false);
		responseDTO.setMsg(MSG_ERROR);
		
		if (userAssembler.isUserBeanValid(userDTO)) {
			
			boolean resp = userAssembler.insertUser(userDTO);
			
			if (resp) {
				responseDTO.setStatus(true);
				responseDTO.setMsg(MSG_OK);
				responseDTO.setObject(resp);
			} else {
				LOG.error("No se logro insertar el usuario");
			}
			
		} else {
			LOG.error("El Bean de usuario no es valido");
		}
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para borrar el usuario tomando como parametro su id
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = true si se borro el usuario, false de lo contrario)
	 */
	public ResponseDTO deleteUserById(String id) {
		
		LOG.info("deleteUserById");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		responseDTO.setStatus(false);
		responseDTO.setMsg(MSG_ERROR);
		
		if(userAssembler.isStrIdValid(id)) {
			
			int intId = userAssembler.getIntFromStr(id);
			
			boolean resp = userAssembler.deleteUserById(intId);
			
			if (resp) {
				
				responseDTO.setStatus(true);
				responseDTO.setMsg(MSG_OK);
				responseDTO.setObject(resp);
				
			} else {
				LOG.error("UserDTO null");
			}
			
		} else {
			LOG.error("El ID no es valido");
		}
		
		LOG.info(responseDTO);
		
		return responseDTO;
	}
	
}

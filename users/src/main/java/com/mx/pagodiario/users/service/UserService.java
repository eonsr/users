package com.mx.pagodiario.users.service;

import com.mx.pagodiario.users.dto.ResponseDTO;
import com.mx.pagodiario.users.dto.UserDTO;

/**
 * Interfaz de servicios para usuario
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public interface UserService {

	/**
	 * Metodo para obtner todos los usuarios registrados
	 * 
	 * @return {@link ResponseDTO} (object = lista de {@link UserDTO})
	 */
	public ResponseDTO getUsers();
	
	/**
	 * Metodo para traer usuario por ID de base de datos
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = {@link UserDTO})
	 */
	public ResponseDTO getUserById(String id);
	
	/**
	 * Metodo para insertar un usuario nuevo
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return {@link ResponseDTO} (object = true si inserto el usuario, false de lo contrario)
	 */
	public ResponseDTO insertUser(UserDTO userDTO);
	
	/**
	 * Metodo para borrar el usuario tomando como parametro su id
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = true si se borro el usuario, false de lo contrario)
	 */
	public ResponseDTO deleteUserById(String id);
	
}

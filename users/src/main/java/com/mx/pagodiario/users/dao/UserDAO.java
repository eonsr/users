package com.mx.pagodiario.users.dao;

import java.util.List;

import com.mx.pagodiario.users.dto.UserDTO;

/**
 * Data Access Object para tabla users 
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public interface UserDAO {

	/**
	 * Metodo para obtner todos los usuarios registrados
	 * 
	 * @return lista de {@link UserDTO}
	 * @throws Exception Error en la ejecucion
	 */
	public List<UserDTO> getUsers() throws Exception;
	
	
	/**
	 * Metodo para traer usuario por ID de base de datos
	 * 
	 * @param id ID del usuario
	 * @return {@link UserDTO}
	 * @throws Exception Error en la ejecucion
	 */
	public UserDTO getUserById(Integer id) throws Exception;
	
	
	/**
	 * Metodo para insertar un usuario nuevo
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return numero de registros afectados
	 * @throws Exception Error en la ejecucion
	 */
	public int insertUser(UserDTO userDTO) throws Exception;
	
	
	/**
	 * Metodo para borrar el usuario tomando como parametro su id
	 * 
	 * @param id ID del usuario
	 * @return numero de registros afectados
	 * @throws Exception Error en la ejecucion
	 */
	public int deleteUserById(Integer id) throws Exception;
	
}

package com.mx.pagodiario.users.assembler;

import static com.mx.pagodiario.users.constants.UserConstants.CONST_INT_ZERO;
import static com.mx.pagodiario.users.constants.UserConstants.REGEX_INT;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mx.pagodiario.users.dao.UserDAO;
import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.utils.ReflectionUtil;

/**
 * Assembler para logica de negocio de usuario
 *  
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Component
@Scope("prototype")
public class UserAssembler {

	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserAssembler.class);
	
	/**
	 * {@link UserDAO}
	 */
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * {@link ReflectionUtil}
	 */
	@Autowired
	private ReflectionUtil reflectionUtil;
	
	/**
	 * Metodo para obtner todos los usuarios registrados
	 * 
	 * @return lista de {@link UserDTO}
	 */
	public List<UserDTO> getUsers() {
		
		LOG.info("getUsers");
		
		List<UserDTO> usersList = null;
		
		try {
			usersList = userDAO.getUsers();
		} catch (Exception e) {
			LOG.error(e);
		}
		
		LOG.debug("Lista de usuarios [" + usersList + "]");
		
		return usersList;
		
	}

	/**
	 * Metodo para traer usuario por ID de base de datos
	 * 
	 * @param id ID del usuario
	 * @return {@link UserDTO}
	 */
	public UserDTO getUserById(int id) {
		
		LOG.info("getUserById");
		
		UserDTO userDTO = null;
		
		if (id > CONST_INT_ZERO) {
			
			try {
				userDTO = userDAO.getUserById(id);
			} catch (Exception e) {
				LOG.error(e);
			}
			
		} else {
			LOG.info("El id no es valido");
		}
		
		LOG.debug("Usuario obtenido [" + userDTO + "]");
		
		return userDTO;
		
	}

	/**
	 * Metodo para insertar un usuario nuevo
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return true si inserto el usuario, false de lo contrario
	 */
	public Boolean insertUser(UserDTO userDTO) {
		
		LOG.info("insertUser");
		
		Boolean resp = false;
		
		int rowsAffected = 0;
		
		if (userDTO != null) {
			
			try {
				
				rowsAffected = userDAO.insertUser(userDTO);
				
				if(rowsAffected > CONST_INT_ZERO) {
					resp = true;
				} else {
					LOG.error("No se inserto el usuario " + userDTO);
				}
				
			} catch (Exception e) {
				LOG.error(e);
			}
			
		} else {
			LOG.error("El DTO no puede ser null");
		}
		
		LOG.debug("insertUser? [" + resp + "]");
		
		return resp;
		
	}

	/**
	 * Metodo para borrar el usuario tomando como parametro su id
	 * 
	 * @param id ID del usuario
	 * @return true si se borro el usuario, false de lo contrario
	 */
	public Boolean deleteUserById(int id) {
		
		LOG.info("deleteUserById");
		
		Boolean resp = false;
		
		int rowsAffected = 0;
		
		if (id > CONST_INT_ZERO) {
			
			try {
				
				rowsAffected = userDAO.deleteUserById(id);
				
				if(rowsAffected > CONST_INT_ZERO) {
					resp = true;
				} else {
					LOG.error("No se borro el " + id);
				}
				
			} catch (Exception e) {
				LOG.error(e);
			}
			
		} else {
			LOG.info("El id no es valido");
		}
		
		LOG.debug("deleteUserById? [" + resp + "]");
		
		return resp;
		
	}
	/**
	 * Metodo para generar un entero a partir de un String
	 *  
	 * @param str String a transformar
	 * @return Entero generado
	 */
	public int getIntFromStr(String str) {
	
		LOG.info("getIntFromStr");
		
		int intGenerated = 0;
		
		if (str != null && !str.isEmpty()) {
			
			if (str.matches(REGEX_INT)) {
				intGenerated = Integer.parseInt(str);
			} else {
				LOG.info("formato incorrecto");
			}
			
		} else {
			LOG.error("La cadena no puede ser null ni estar vacia");
		}
		
		LOG.info("Entero Generado [" + intGenerated + "]");
		
		return intGenerated;
		
	}
	
	/**
	 * Metodo para valdiar el {@link UserDTO} de entrada
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return true si es valido, false de lo contrario
	 */
	public boolean isUserBeanValid(UserDTO userDTO) {
		
		LOG.info("isUserBeanValid");
		
		boolean resp = false;
		
		Map<String, Method> methodsMap = reflectionUtil.getMethodsWithGetMap(userDTO);
		
		int error = 0;
		
		if(userDTO != null) {
			
			for(Map.Entry<String, Method> entry : methodsMap.entrySet()) {
				
				try {
					
					Object obj = reflectionUtil.invokeMethod(entry.getKey(), null, userDTO);
					
					LOG.debug("Metodo [" + entry.getKey() + "] = [" + obj + "]");
					
					if (obj != null) {
						
						if (obj instanceof String && ((String) obj).isEmpty()) {
							error++;
							LOG.error("Cadena vacia en metodo [" + entry.getKey() + "]");
						} 
						
						
					} else {
						error++;
						LOG.error("null en metodo [" + entry.getKey() + "]");
					}
					
				} catch (Exception e) {
					LOG.error(e);
				}
				
			}
			
		} else {
			LOG.error("Bean es null");
			error++;
		}
		
		
		LOG.debug("cantidad de errores encontrados [" + error + "]");
		
		if (error == 0 ) {
			resp = true;
		} else {
			LOG.error("Bean no valido");
		}
		
		return resp;
		
	}
	
	/**
	 * Metodo para valdiar el ID de tipo String
	 * 
	 * @param id string con id
	 * @return true si es valido false de lo contrario
	 */
	public boolean isStrIdValid(String id) {
		
		LOG.info("isStrIdValid");
		
		boolean resp = false;
		
		if(id != null && !id.isEmpty()) {
			
			if(id.matches(REGEX_INT)) {
				resp = true;
			} else {
				LOG.error("Formato de ID incorrecto");
			}
			
		} else {
			LOG.error("El ID no debe ser nulo ni estar vacio");
		}
		
		LOG.info("isStrIdValid? [" + resp +"]");
		
		return resp;
		
	}
	
}

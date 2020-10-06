package com.mx.pagodiario.users.frontcontroller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.pagodiario.users.dto.ResponseDTO;
import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Front Controller para servicios de usuario 
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@RestController
@CrossOrigin
@Api(value = "Controller de user")
@RequestMapping("/api/v1/user")
public class UserFrontController {
	
	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserFrontController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * Metodo para obtner todos los usuarios registrados
	 * 
	 * @return {@link ResponseDTO} (object = lista de {@link UserDTO})
	 */
	@ApiOperation("Regresa el JSON/DTO con respuesta al procesamiento de busqueda de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Successful"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "getUsers")
	public ResponseDTO getUsers() {
		
		LOG.info("getUsers");
		
		ResponseDTO responseDTO = userService.getUsers();
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para traer usuario por ID de base de datos
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = {@link UserDTO})
	 */
	@ApiOperation("Regresa el JSON/DTO con respuesta al procesamiento de busqueda de usuario po ID")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Successful"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "getUserById")
	public ResponseDTO getUserById(String id) {
		
		LOG.info("getUserById");
		
		ResponseDTO responseDTO = userService.getUserById(id);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para insertar un usuario nuevo
	 * 
	 * @param userDTO {@link UserDTO}
	 * @return {@link ResponseDTO} (object = true si inserto el usuario, false de lo contrario)
	 */
	@ApiOperation("Regresa el JSON/DTO con respuesta al procesamiento de insercion de usuario")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Successful"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@PostMapping(value = "insertUser")
	public ResponseDTO insertUser(@RequestBody UserDTO userDTO) {
		
		LOG.info("insertUser");
		
		ResponseDTO responseDTO = userService.insertUser(userDTO);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

	/**
	 * Metodo para borrar el usuario tomando como parametro su id
	 * 
	 * @param id ID del usuario
	 * @return {@link ResponseDTO} (object = true si se borro el usuario, false de lo contrario)
	 */
	@ApiOperation("Regresa el JSON/DTO con respuesta al procesamiento de borrado de usuario por ID")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Successful"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(value = "deleteUserById")
	public ResponseDTO deleteUserById(@RequestBody String id) {
		
		LOG.info("deleteUserById");
		
		ResponseDTO responseDTO = userService.deleteUserById(id);
		
		LOG.info(responseDTO);
		
		return responseDTO;
		
	}

}

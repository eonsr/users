package com.mx.pagodiario.users.test.bo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.pagodiario.users.assembler.UserAssembler;
import com.mx.pagodiario.users.bo.UserBO;
import com.mx.pagodiario.users.dao.UserDAO;
import com.mx.pagodiario.users.dto.ResponseDTO;
import com.mx.pagodiario.users.dto.UserDTO;

/**
 * Clase para probar {@link UserBO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@SpringBootTest
public class UserBOTest {

	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserBOTest.class);
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private UserAssembler userAssembler;
	
	@InjectMocks
	private UserBO userBO = new UserBO();
	
	/**
	 * id de prueba
	 */
	private int id = 1;
	
	/**
	 * id de prueba string
	 */
	private String idStr = "1";
	
	/**
	 * id de prueba string
	 */
	private String idStrError = "1a";
	
	/**
	 * Bean de prueba
	 */
	private UserDTO testDTO;
	
	@BeforeEach
	void setMockOuput() {
		
		LOG.info("setMockOutput");
		
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		
		usersList.add(new UserDTO(1, "John", "Doe", "Johnson", "123456", new Date()));
		usersList.add(new UserDTO(2, "Jane", "Doe", "Johnson", "123457", new Date()));
		
		testDTO = new UserDTO(1, "John", "Doe", "Johnson", "123456", new Date());
		
		when(userAssembler.getUsers()).thenReturn(usersList);
		
		when(userAssembler.getIntFromStr(idStr)).thenReturn(id);
		
		when(userAssembler.isStrIdValid(idStr)).thenReturn(true);
		
		when(userAssembler.getUserById(id)).thenReturn(testDTO);
		
		when(userAssembler.deleteUserById(id)).thenReturn(true);
		
		when(userAssembler.isStrIdValid(idStrError)).thenCallRealMethod();
		
		when(userAssembler.getIntFromStr(idStrError)).thenCallRealMethod();
		
		when(userAssembler.insertUser(testDTO)).thenReturn(true);
		
		when(userAssembler.isUserBeanValid(testDTO)).thenReturn(true);
		
	}
	
	/**
	 * metodo para probar getUsers()
	 */
//	@Test
	void testGetUsers() {
		
		LOG.info("testGetUsers");
		
		ResponseDTO responseDTO = userBO.getUsers();
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() != null && responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
	}
	
	/**
	 * Metodo para probar getUserById
	 */
//	@Test
	void testGetUserById() {
		
		LOG.info("testGetUserById");
	
		ResponseDTO responseDTO = userBO.getUserById(idStr);
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() != null && responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
		responseDTO = userBO.getUserById(idStrError);
		
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() == null && !responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
	}
	
	/**
	 * Metodo para probar deleteUserById()
	 */
//	@Test
	void testDeleteUserById() {
		
		LOG.info("testDeleteUserById");
		
		ResponseDTO responseDTO = userBO.deleteUserById(idStr);
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() != null && responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
		responseDTO = userBO.deleteUserById(idStrError);
		
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() == null && !responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
	}
	
	/**
	 * metodo para probar insertUser()
	 */
	@Test
	void testInsertUser() {
		
		LOG.info("testInsertUser");
		
		ResponseDTO responseDTO = userBO.insertUser(testDTO);
		
		LOG.info("responseDTO obtenido [" + responseDTO + "]");
		
		assertTrue(responseDTO != null && responseDTO.getObject() != null && responseDTO.isStatus(), 
				"Se esperaba un resultado");
		
	}
	
}

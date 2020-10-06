package com.mx.pagodiario.users.test.assembler;

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
import com.mx.pagodiario.users.dao.UserDAO;
import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.utils.ReflectionUtil;

/**
 * Clase de prueba para {@link UserAssembler} (con Mock)
 * 
 * @author Noe Salazar Ramriez
 * 
 * @version 1.0
 *
 */
@SpringBootTest
public class UserAssemblerMockTest {

	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserAssemblerMockTest.class);
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private ReflectionUtil reflectionUtil =  new ReflectionUtil();
	
	@InjectMocks
	private UserAssembler userAssembler = new UserAssembler();
	
	/**
	 * registros afectados de prueba
	 */
	private int rowsAffected = 1;
	
	/**
	 * id de prueba
	 */
	private int id = 1;
	
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
		
		try {
			when(userDAO.getUsers()).thenReturn(usersList);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		try {
			when(userDAO.getUserById(id)).thenReturn(testDTO);
		} catch (Exception e) {
			LOG.error(e);
		}
	
		try {
			when(userDAO.deleteUserById(id)).thenReturn(rowsAffected);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		try {
			when(userDAO.insertUser(testDTO)).thenReturn(rowsAffected);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		when(userAssembler.isUserBeanValid(testDTO)).thenCallRealMethod();
		
	}
	
	/**
	 * metodo para probar getUsers()
	 */
	@Test
	void testGetUsers() {
		
		LOG.info("testGetUsers");
		
		List<UserDTO> usersList = userAssembler.getUsers();
		
		LOG.info("usersList obtenida [" + usersList + "]");
		
		assertTrue(usersList != null && !usersList.isEmpty(), "Se esperaba un true");
		
	}
	
	/**
	 * Metodo para probar getUserById
	 */
	@Test
	void testGetUserById() {
		
		LOG.info("testGetUserById");
		
		UserDTO userDTO = userAssembler.getUserById(id);
		
		LOG.info("UserDTO obtenido [" + userDTO + "]");
		
		assertTrue(userDTO != null && userDTO.getName() != null && !userDTO.getName().isEmpty(), 
				"El nombre no deberia ser null ni estar vacio");
		
	}
	
	/**
	 * Metodo para probar deleteUserById()
	 */
	@Test
	void testDeleteUserById() {
		
		LOG.info("testDeleteUserById");
		
		boolean del = userAssembler.deleteUserById(id);
		
		assertTrue(del, "Se esperaba un true");
		
	}
	
	/**
	 * metodo para probar insertUser()
	 */
	@Test
	void testInsertUser() {
		
		LOG.info("testInsertUser");
		
		boolean del = userAssembler.insertUser(testDTO);
		
		assertTrue(del, "Se esperaba un true");
		
	}
	
}

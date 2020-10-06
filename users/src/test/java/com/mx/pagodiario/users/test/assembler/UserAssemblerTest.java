package com.mx.pagodiario.users.test.assembler;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.pagodiario.users.assembler.UserAssembler;
import com.mx.pagodiario.users.dto.UserDTO;

/**
 * Clase de prueba para {@link UserAssembler} (sin Mock)
 * 
 * @author Noe Salazar Ramriez
 * 
 * @version 1.0
 *
 */
@SpringBootTest
public class UserAssemblerTest {

	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserAssemblerTest.class);
	
	/**
	 * 
	 */
	@Autowired
	private UserAssembler userAssembler;
	
	/**
	 * Metodo para probar isStrIdValid()
	 */
//	@Test
	void testIsStrIdValid() {
		
		LOG.info("testIsStrIdValid");
		
		String idTest = "123456"; 
		
		boolean resp = userAssembler.isStrIdValid(idTest);
		
		assertTrue(resp, "Se esperaba un true");
		
		idTest = "ID-123456";
		
		resp = userAssembler.isStrIdValid(idTest);
		
		assertFalse(resp, "Se esperaba un false");
		
	}
	
	/**
	 * metodo para probar getIntFromStr()
	 */
//	@Test
	void testGetIntFromStr() {
		
		LOG.info("testGetIntFromStr");
		
		String str = "123456";
		
		int intGenerated = userAssembler.getIntFromStr(str);
		
		LOG.info("Entero generado [" + intGenerated + "]");
		
		assertTrue(intGenerated == Integer.parseInt(str), "el entero debe ser igual");
		
		str = "ID-123456";
		
		intGenerated = userAssembler.getIntFromStr(str);
		
		LOG.info("Entero generado [" + intGenerated + "]");
		
		assertTrue(intGenerated == 0, "el entero debe ser 0");
		
	}
	
	/**
	 * Metodo para probar isUserBeanValid()
	 */
	@Test
	void testIsUserBeanValid() {
		
		LOG.info("testIsUserBeanValid");
		
		boolean val = userAssembler.isUserBeanValid(new UserDTO(1, "John", "Doe", "Johnson", "123456", new Date()));
		
		LOG.info("isUserBeanValid? [" + val + "]");
		
		assertTrue(val, "se esperaba un true");
		
		val = userAssembler.isUserBeanValid(null);
		
		LOG.info("isUserBeanValid? [" + val + "]");
		
		assertFalse(val, "Se esperaba un false");
		
		val = userAssembler.isUserBeanValid(new UserDTO(1, "", "Doe", "Johnson", "123456", new Date()));
		
		LOG.info("isUserBeanValid? [" + val + "]");
		
		assertFalse(val, "Se esperaba un false");
		
		val = userAssembler.isUserBeanValid(new UserDTO(1, "", "Doe", "Johnson", "123456", null));
		
		LOG.info("isUserBeanValid? [" + val + "]");
		
		assertFalse(val, "Se esperaba un false");
		
	}
	
}

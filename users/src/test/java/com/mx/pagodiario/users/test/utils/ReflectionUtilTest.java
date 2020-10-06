package com.mx.pagodiario.users.test.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.pagodiario.users.test.dto.ExampleDTO;
import com.mx.pagodiario.users.utils.ReflectionUtil;

/**
 * Clase para probar {@link ReflectionUtil}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@SpringBootTest
public class ReflectionUtilTest {

	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(ReflectionUtilTest.class);
	
	@Autowired
	private ReflectionUtil reflectionUtil;
	
	/**
	 * Metodo para probar methodHasParameters
	 */
	@Test
	public void testMethodHasParameters() {
		
		LOG.info("testMethodHasParameters");
		
		boolean resp = false;
		
		Map<String, Method> methodsMap = new TreeMap<String, Method>();
		
		methodsMap = reflectionUtil.getMethodsMap(new String());
		
		assertTrue(methodsMap != null, "El mapa deberia ser diferente de null");
		
		Method method = methodsMap.get("valueOf");
		
		assertTrue(method != null, "El metodo deberia ser diferente de null");
		
		resp = reflectionUtil.methodHasParameters(method);
		
		LOG.info("El metodo [" + method + "] contiene parametros? -> " + resp);
		
		assertTrue(resp, "El metodo deberia contener parametros");
		
		method = methodsMap.get("trim");
		
		assertTrue(method != null, "El metodo deberia ser diferente de null");
		
		resp = reflectionUtil.methodHasParameters(method);
		
		LOG.info("El metodo [" + method + "] contiene parametros? -> " + resp);
		
		assertFalse(resp, "El metodo no deberia contener parametros");
		
	}
	
	/**
	 * metodo para probar buildSetMethod
	 */
	@Test
	public void testBuildSetMethod() {
		
		LOG.info("testBuildSetMethod");
		
		String setMethod = null;
		
		String field = "age";
		
		setMethod = reflectionUtil.buildSetMethod(field);
		
		LOG.info("setMethod " + setMethod);
		
	}
	
	/**
	 * metodo para probar buildFieldMethodMap
	 */
	@Test
	public void testBuildFieldMethodMap() {
		
		LOG.info("testBuildFieldMethodMap");
		
		Map<String, Method> methodsMap = new TreeMap<String, Method>();
		
		Map<String, Field> fieldsMap = new TreeMap<String, Field>();
		
		methodsMap = reflectionUtil.getMethodsMap(new ExampleDTO());
		
		fieldsMap = reflectionUtil.getFieldsMap(new ExampleDTO());
		
		Map<String, Method> fieldMethodMap = reflectionUtil.buildFieldMethodMap(fieldsMap, methodsMap);
		
		LOG.info("fieldMethodMap " + fieldMethodMap);
		
	}
	
}

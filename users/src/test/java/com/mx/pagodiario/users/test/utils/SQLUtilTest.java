package com.mx.pagodiario.users.test.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.pagodiario.users.utils.SQLUtil;

/**
 * Clase de prueba para {@link SQLUtil}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 */
@SpringBootTest
public class SQLUtilTest {
	
	/**
	 * Logger de la clase
	 */
	final static Logger LOG = Logger.getLogger(SQLUtilTest.class);
	
	/**
	 * Utileria para lectura de archivos *.sql 
	 */
	@Autowired
	private SQLUtil sqlUtil;
	
	/**
	 * Archivo con querys con codificacion incorrecta
	 */
	private static String fileWrong = "/sql/QueriesError.sql";
	/**
	 * Archivo con querys correcto
	 */
	private static String fileOk = "/sql/Queries.sql";
	/**
	 * Archivo con querys de Users
	 */
	private static String fileUsers = "/sql/QueriesUsers.sql";
	/**
	 * Primer Query
	 */
	@SuppressWarnings("unused")
	private static String query1 = "Select * \r\n" + 
			"from Table\r\n" + 
			"algo\r\n" + 
			"	algo\r\n" + 
			"algo\r\n" + 
			"	y algo mas\r\n" + 
			"? ?";
	/**
	 * Segundo Query
	 */
	private static String query2 = "select * from Table2";
	
	/**
	 * Nombre del query 1 de Users
	 */
	private static String queryName1User = "getAllUsers";
	
	/**
	 * Nombre del query 1
	 */
	private static String queryName1 = "Query1";
	
	/**
	 * Nombre del query 2
	 */
	private static String queryName2 = "Query2";
	
	/**
	 * Nombre del query 3
	 */
	private static String invalidQueryName = "asdlkjfl";
	
	/**
	 * Metodo de prueba
	 * 
	 * @throws IOException
	 */
	@Test
	public void testGetQuery() {
		
		LOG.info("testGetQuery");
		
		try {
			
			String sql = "";
			
			sql = sqlUtil.getQueryTest(queryName1,fileWrong);
			
			assertTrue(sql == null || sql.isEmpty(), 
					"Se espera un null debido a que usa un archivo con codificacion incorrecta.");
			
			LOG.info("Prueba 2");
			
			sql= sqlUtil.getQueryTest(invalidQueryName,fileOk);
			
			LOG.info("Query prueba 2");
			LOG.info("sql:\n" + sql);
			
			assertTrue(sql == null || sql.isEmpty(), 
					"Se espera un null o vacio debido a que pasamos un nombre de query que no existe.");
			
			LOG.info("Prueba 4");
			
			sql = sqlUtil.getQueryTest(queryName2,fileOk);
			
			LOG.info("Query prueba 4");
			LOG.info("sql:\n" + sql);
			
			assertEquals(sql, query2, 
					"Se espera que el query2 coincida");
			LOG.info("Prueba 5");
			sql = sqlUtil.getQuery(queryName1User, fileUsers);
		} catch (IOException e) {
			LOG.error(e);
		}
		
		
	}
}

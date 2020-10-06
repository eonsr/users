package com.mx.pagodiario.users.utils;

import static com.mx.pagodiario.users.constants.SqlUtilConstants.END_QUERY_PATTERN;
import static com.mx.pagodiario.users.constants.SqlUtilConstants.START_QUERY_PATTERN;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Utileria para lectura de archivos *.sql 
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 */
@Component
@Scope(value = "prototype")
public class SQLUtil {

	/**
	 * Logger de la clase
	 */
	private static final Logger LOG = Logger.getLogger(SQLUtil.class);

	/**
	 * Lista de Querys
	 */
	private Map<String, String> querysMap;

	
	/**
	 * Retorna el query de acuerdo al nombre proporcionado
	 * 
	 * @param queryName Nombre del query
	 * @param sqlFile archivo a procesar
	 * @return Query 
	 * @throws IOException Problemas con el archivo
	 */
	public String getQuery(String queryName, String sqlFile) throws IOException {

		LOG.info("getQuery");

		getQuerys(sqlFile);

		String query = querysMap.get(queryName);

		LOG.debug(querysMap.containsKey(queryName));

		LOG.info("\nQuery [" + query + "]");

		return query;
		
	}

	
	/**
	 * 
	 * @param queryName Nombre del query
	 * @param sqlFile archivo a procesar
	 * @return Query
	 * @throws IOException Problemas con el archivo
	 */
	public String getQueryTest(String queryName, String sqlFile) throws IOException {

		LOG.info("getQueryTest");

		querysMap = null;

		String query = getQuery(queryName, sqlFile);

		LOG.debug(query);

		return query;
		
	}

	/*
	 * M�todos privados
	 */

	/**
	 * Valida que la lista de querys no est� vac�a antes de su uso
	 * 
	 * @throws IOException Problemas con el archivo
	 */
	private void getQuerys(String sqlFile) throws IOException {

		LOG.info("getQuerys");

		if (querysMap == null) {
			getMap(sqlFile);
		}

		LOG.debug("QuerysMap [" + querysMap.toString() + "]");
		
	}

	/**
	 * Obtiene la lista de querys en el archivo
	 * 
	 * @throws IOException Problemas con el archivo
	 */
	private void getMap(String sqlFile) throws IOException {

		LOG.info("getMap");

		String fileContent = loadFile(sqlFile);
		String[] querys = getStringArray(fileContent, END_QUERY_PATTERN);
		fillMap(querys);

		LOG.info("END getMap");
		
	}

	/**
	 * Lee la informaci�n del archivo
	 * 
	 * @return String con la informaci�n del archivo
	 * 
	 * @throws IOException Problemas con el archivo
	 */
	private String loadFile(String sqlFile) throws IOException {

		LOG.info("loadFile");

		String fileContent = "";

		InputStream is = this.getClass().getResourceAsStream(sqlFile);

		byte[] data = new byte[is.available()];

		int resp = is.read(data);
		
		LOG.debug("Bytes leidos : " + resp);

		is.close();

		fileContent = new String(data);

		LOG.debug("FileContent [" + fileContent + "]");

		return fileContent;
		
	}

	/**
	 * Divide el String proporcionado en un array de acuerdo al regex
	 * 
	 * @param data  String a dividir
	 * @param regex String divisor
	 * @return Array de String
	 */
	private String[] getStringArray(String data, String regex) {

		LOG.info("getStringArray");
		
		String[] array = data.split(regex);

		LOG.debug("Array [" + Arrays.toString(array) + "]");

		return array;
		
	}

	/**
	 * Llena el Map de querys con la informacion del array proporcionado
	 * 
	 * @param querys Array de querys
	 */
	private void fillMap(String[] querys) {

		LOG.info("fillMap");

		querysMap = new HashMap<String, String>();
		
		for (String query : querys) {
			
			String[] temp = getStringArray(query, START_QUERY_PATTERN);
			
			if (temp != null && temp.length == 2) {
				
				temp[0] = temp[0].replaceAll("\\P{Alnum}", "");
				querysMap.put(temp[0], temp[1].trim());
				
			} else {
				LOG.error("El array es nulo o la longitud no es la esperada");
			}
			
		}

		LOG.info("END fillMap");

	}

}

package com.mx.pagodiario.users.enums;

/**
 * Enum para la tabla user
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public enum TableUserEnum {
	
	/**
	 * campo user_id
	 */
	USER_ID("user_id"),
	
	/**
	 * campo
	 */
	USER_NAME("user_name"),
	
	/**
	 * campo
	 */
	USER_LAST_NAME("user_last_name"),
	
	/**
	 * campo
	 */
	USER_MOTHER_LAST_NAME("user_mother_last_name"),
	
	/**
	 * campo
	 */
	USER_RFC("user_rfc"),
	
	/**
	 * campo
	 */
	USER_BIRTH_DATE("user_birth_date");
	
	/**
	 * Valor del nombre del campo de la tabla
	 */
	private String value;
	
	/**
	 * Constructor
	 * 
	 * @param value nombre del campo
	 */
	private TableUserEnum(String value) {
		this.value = value;
	}

	/**
	 * Devuelve el nombre del campo de la tabla
	 * 
	 * @return String
	 */
	public String getValue() {
		return value;
	}
	
}

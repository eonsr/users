package com.mx.pagodiario.users.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object para usuarios
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	/**
	 * ID del usuario
	 */
	private Integer id;
	
	/**
	 * Nombre del usuario
	 */
	private String name;
	
	/**
	 * Apellido paterno del usuario
	 */
	private String lastName;
	
	/**
	 * Apellido materno del usuario
	 */
	private String motherLastName;
	
	/**
	 * RFC del usuario
	 */
	private String rfc;
	
	/**
	 * Fecha de nacimiento del usuario
	 */
	private Date birthDate;
	
}

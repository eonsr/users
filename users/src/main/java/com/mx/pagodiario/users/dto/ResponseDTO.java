package com.mx.pagodiario.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object para respuesta general
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
public class ResponseDTO {
	
	/**
	 * Estatus de la respuesta
	 */
	private boolean status;
	
	/**
	 * Mensaje
	 */
	private String msg;
	
	/**
	 * Objeto regresado (podria ser null)
	 */
	private Object object;
	
}

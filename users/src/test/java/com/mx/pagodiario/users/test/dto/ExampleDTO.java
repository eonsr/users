package com.mx.pagodiario.users.test.dto;


/**
 * DTO de pruebas
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public class ExampleDTO {
	
	/**
	 * nombre
	 */
	private String name;
	
	/**
	 * edad
	 */
	private int age;
	
	/**
	 * password
	 */
	private String pass;
	
	/*
	 * ========================================================================
	 * 							getters & setters
	 * ========================================================================
	 */
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/*
	 * ========================================================================
	 * 							getters & setters
	 * ========================================================================
	 */
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("ExampleDTO [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", pass=");
		builder.append(pass);
		builder.append("]");
		
		return builder.toString();
		
	}

}

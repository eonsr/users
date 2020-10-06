package com.mx.pagodiario.users.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.enums.TableUserEnum;

/**
 * Mapper para la tabla users
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public class TableUserMapper implements RowMapper<UserDTO> {

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserDTO userDTO = new UserDTO();
		
		Integer id = rs.getInt(TableUserEnum.USER_ID.getValue());
		String name = rs.getString(TableUserEnum.USER_NAME.getValue());
		String lastName = rs.getString(TableUserEnum.USER_LAST_NAME.getValue());
		String motherLastName = rs.getString(TableUserEnum.USER_MOTHER_LAST_NAME.getValue());
		String rfc = rs.getString(TableUserEnum.USER_RFC.getValue());
		Date birthDate = rs.getDate(TableUserEnum.USER_BIRTH_DATE.getValue());
		
		userDTO.setId(id);
		userDTO.setName(name);
		userDTO.setLastName(lastName);
		userDTO.setMotherLastName(motherLastName);
		userDTO.setRfc(rfc);
		userDTO.setBirthDate(birthDate);
		
		return userDTO;
		
	}

}

package com.mx.pagodiario.users.dao.imp;

import static com.mx.pagodiario.users.constants.UserQueriesConstants.QUERY_DELETE_USER_BY_ID;
import static com.mx.pagodiario.users.constants.UserQueriesConstants.QUERY_GET_USERS;
import static com.mx.pagodiario.users.constants.UserQueriesConstants.QUERY_GET_USER_BY_ID;
import static com.mx.pagodiario.users.constants.UserQueriesConstants.QUERY_INSERT_USER;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mx.pagodiario.users.dao.UserDAO;
import com.mx.pagodiario.users.dto.UserDTO;
import com.mx.pagodiario.users.mapper.TableUserMapper;
import com.mx.pagodiario.users.utils.SQLUtil;

/**
 * Implementacion de {@link UserDAO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Repository
public class UserDAOImp implements UserDAO {
	
	/**
	 * Log para la clase
	 */
	private static final Logger LOG = Logger.getLogger(UserDAOImp.class);
	
	/**
	 * {@link JdbcTemplate}
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Utileria para lectura de archivos *.sql
	 */
	@Autowired
	private SQLUtil sqlUtil;
	
	/**
	 * Archivo SQL
	 */
	@Value("${sql.file.path}")
	private String sqlFile;
	
	/**
	 * String para sql
	 */
	private String sql;

	@Override
	public List<UserDTO> getUsers() throws Exception {
		
		LOG.info("getUsers");
		
		sql = sqlUtil.getQuery(QUERY_GET_USERS, sqlFile);
		
		List<UserDTO> usersList = jdbcTemplate.query(sql, new TableUserMapper());
		
		LOG.debug("Lista de usuarios [" + usersList + "]");
		
		return usersList;
		
	}

	@Override
	public UserDTO getUserById(Integer id) throws Exception {
		
		LOG.info("getUserById");
		
		sql = sqlUtil.getQuery(QUERY_GET_USER_BY_ID, sqlFile);
		
		Object[] params = {id};
		
		UserDTO userDTO = jdbcTemplate.queryForObject(sql, params, new TableUserMapper());
		
		LOG.debug("Usuario obtenido [" + userDTO + "]");
		
		return userDTO;
		
	}

	@Override
	public int insertUser(UserDTO userDTO) throws Exception {
		
		LOG.info("insertUser");
		
		sql = sqlUtil.getQuery(QUERY_INSERT_USER, sqlFile);
		
		Object[] params = { 
				userDTO.getName(),
				userDTO.getLastName(),
				userDTO.getMotherLastName(),
				userDTO.getRfc(),
				userDTO.getBirthDate()
		};
		
		int rowsAffected = jdbcTemplate.update(sql, params);
		
		LOG.debug("rowsAffected [" + rowsAffected + "]");
		
		return rowsAffected;
		
	}

	@Override
	public int deleteUserById(Integer id) throws Exception {
		
		LOG.info("deleteUserById");
		
		sql = sqlUtil.getQuery(QUERY_DELETE_USER_BY_ID, sqlFile);
		
		Object[] params = {id};
		
		int rowsAffected = jdbcTemplate.update(sql, params);
		
		LOG.debug("rowsAffected [" + rowsAffected + "]");
		
		return rowsAffected;
		
	}

}

package com.quickpark.in.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.quickpark.in.Model.Login;


@Repository
public class LoginDaoImple implements LoginDao {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public Login validateUser(Login login) {
		Login log1=null;
		 
	      	String sql = "select * from Master where UserName=? and Password=?";
	      		Login log=new Login();
		try {
	      		 log1=jdbctemplate.queryForObject(sql, new Object[]{login.getUserName(),login.getPassword()}, new RowMapper<Login>() {
	      		//Login  log1=(Login) jdbctemplate.query(sql, new Object[]{login.getUserName(),login.getPassword()}, new RowMapper<Login>() {
				@Override
				public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					Login log=new Login();
					log.setUserName(rs.getString(2));
					log.setPassword(rs.getString(3));
					log.setRole(rs.getString(4));
					
					return log;
					}
					
	      		
				});
		}catch(Exception e)
		{
			log=null;
			log1= log;
		}
	      		
		    	
		    	
		   
			return log1;
	
		
		
		
	}
}

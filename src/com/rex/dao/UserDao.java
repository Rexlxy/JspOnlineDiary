package com.rex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rex.model.User;

public class UserDao {

	public User login(Connection con, User user) throws SQLException{
		User resultUser = null;
		String sql = "select * from t_user where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			resultUser = new User(rs.getString("userName"),rs.getString("password"));
		}
		con.close();
		return resultUser;
		
	}
}

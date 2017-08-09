package com.rex.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rex.model.User;
import com.rex.util.MD5Util;

public class UserDao {

	public User login(Connection con, User user) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
		User resultUser = null;
		String sql = "select * from t_user where userName=? and password=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, MD5Util.encodePwdByMD5(user.getPassword()));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			resultUser = new User(rs.getString("userName"),rs.getString("password"));
			resultUser.setNickName(rs.getString("nickName"));
			resultUser.setImageName(rs.getString("imageName"));
			resultUser.setMood(rs.getString("mood"));
		}
		con.close();
		return resultUser;
		
	}
}

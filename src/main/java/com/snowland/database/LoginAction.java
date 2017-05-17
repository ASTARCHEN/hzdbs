package com.snowland.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.snowland.beans.Teacher;
import com.snowland.beans.User;
import com.snowland.util.BaseConnection;

public class LoginAction {
//	BaseConnection base;
	Connection conn;
	public LoginAction(){
		BaseConnection base = new BaseConnection();
		conn = base.getConnection();
	}
	public User getLogin(String username,String pass){
		try {
			Statement state = conn.createStatement();
			String sql = "select * from user where username = '"+username+"' and password = '"+pass+"'";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet set = ps.executeQuery();
//			set.next();
			if(set.next()){
				return null;
			} else {
//				String username = set.getString("username");
				String part = set.getString("part");
				int id = set.getInt("id");
				return new User(id,username,null,part);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

package com.dn.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dn.web.dao.UserDao;
import com.dn.web.model.User;
import com.dn.web.tools.DB;


public class UserService implements UserDao{
	private static String SEARCH="select * from login where user=? and passwords=?";
	private static String INSERT="insert into  login values(?,?,?)";
	@Override
	public User check(String username,String password) {	 
	//模拟数据，或者这里的数据可以来自数据库！！！！！
	ResultSet re=DB.executeQuery(SEARCH,username,password);	
	 try {
		if(re.next()){
				  return new User(re.getInt("userid"),re.getString("user"),re.getString("passwords"));
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close();
		}
	
	return null;
	
	}
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		int re=DB.executeUpdate(INSERT, user.getUserid(),user.getName(),user.getPass());

		return re;
	}

}

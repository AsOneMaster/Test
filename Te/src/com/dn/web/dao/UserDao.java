package com.dn.web.dao;



import java.util.List;

import com.dn.web.model.User;



public interface UserDao { 
	
	User check(String username, String password);
	
	int insert(User user);
	
	
}

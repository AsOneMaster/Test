package com.dn.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dn.web.model.User;
import com.dn.web.service.UserService;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class LoginServlet extends  HttpServlet{
	 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//客户端json处于doget
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("name", "admin");
//		jsonObject.put("pass","1234");
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("application/json");
//        //resp.getWriter().write(jsonArray.toString());
//        resp.getWriter().println(jsonObject.toString());
//        resp.getWriter().close();
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");  
	    resp.setCharacterEncoding("UTF-8"); 
	    JSONObject jsonObject=new JSONObject();
	  
		String username=req.getParameter("userName");
		String password=req.getParameter("passWord");
		UserService userService=new UserService();
		User user=userService.check(username, password);
		 
		if (user==null) {	
			
			jsonObject.put("login", "fail");
			resp.getWriter().println(jsonObject.toString());
	        resp.getWriter().close();
			
		}else{
			
			int userid=user.getUserid();
			jsonObject.put("login", "success");
			jsonObject.put("userid", userid);
			resp.getWriter().println(jsonObject.toString());	       
			//System.out.println("用户："+userid+"已登录。。。");							
			resp.getWriter().close();
			
		}
	}
	

}

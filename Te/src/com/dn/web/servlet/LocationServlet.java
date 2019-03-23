package com.dn.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dn.web.model.LocationBean;
import com.dn.web.model.User;
import com.dn.web.service.LocationService;
import com.dn.web.service.UserService;

import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class LocationServlet extends  HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");  
	    resp.setCharacterEncoding("UTF-8");		
		String loc=req.getParameter("loc");
		 //System.out.println(loc);

		JSONObject jsonOb = JSONObject.fromObject(loc);

		LocationBean r_bean = (LocationBean)JSONObject.toBean(jsonOb, LocationBean.class);
//		LocationService lService=new LocationService();
////		lService.insert(r_bean);
//		JSONObject reciveloc=new JSONObject();
//		reciveloc.put("otherid", r_bean.getOtherId());
//		reciveloc.put("la", r_bean.getLatitude());
//		reciveloc.put("lo", r_bean.getLongitude());
//		resp.getWriter().println(reciveloc.toString());
		//System.out.println(":"+r_bean.getDate());

	}
	

}

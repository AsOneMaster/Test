package com.dn.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dn.web.model.LocationBean;
import com.dn.web.service.LocationService;

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
		JSONObject jsonOb = JSONObject.fromObject(loc);
		JSONObject reciveloc=new JSONObject();
		String date=getTime().toString();
		String isStart=req.getParameter("isStart");
			
		LocationBean r_bean = (LocationBean)JSONObject.toBean(jsonOb, LocationBean.class);
		LocationService lService=new LocationService();
			
			if(isStart.equals("yes")) {
				lService.insert_start(r_bean);
				 System.out.println(loc+"222222");
			}
			if(isStart.equals("no")) {
				 System.out.println(loc);
				lService.insert_end(r_bean,date);			
			}
			if(isStart.equals("con")){
				List<LocationBean> ls=lService.SerchEvent(Integer.parseInt(req.getParameter("userid")));
				//System.out.println(ls.toString());
				reciveloc.put("show", ls);
				resp.getWriter().println(reciveloc.toString());
			}
		
		
		
		
		
		
		

		
		
		
		
		//System.out.println(":"+r_bean.getDate());

	}
	public String getTime() {
	    Date date = new Date();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return df.format(date);
	}

    public boolean isJson(String content) {
        try {
            JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	

}

package com.dn.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dn.web.dao.locationDao;
import com.dn.web.model.LocationBean;
import com.dn.web.tools.DB;


public class LocationService implements locationDao{
	private static String SEARCH="select * from location where userid=?";
	private static String INSERT="insert into location (userid,addr,locationDescribe,date) values(?,?,?,?)";
	private static String INSERT_END="update location set end_addr=? , end_date=? , end_locationDescribe=? where userid=? and date=?";
	@Override
	public int insert_start(LocationBean bean) {
		// TODO Auto-generated method stub
		int re=DB.executeUpdate(INSERT,bean.getUserId(),bean.getAddr(),bean.getLocationDescribe(),bean.getDate());	
		return re;
	}
	@Override
	public int insert_end(LocationBean bean,String date) {
		// TODO Auto-generated method stub
		int re=DB.executeUpdate(INSERT_END,bean.getEnd_addr(),date,bean.getEnd_locationDescribe(),bean.getUserId(),bean.getDate());		
		return re;
		
	}
	
	/**
	 * 暂时无用
	 */

	@Override
	public List<LocationBean> SerchEvent(int userid) {
		List<LocationBean> lBeans=new ArrayList<LocationBean>();
		// TODO Auto-generated method stub
		ResultSet re=DB.executeQuery(SEARCH,userid);
		try {
			while (re.next()) {
				
				lBeans.add(new LocationBean(re.getInt("userid"),re.getString("addr"),re.getString("locationDescribe"),re.getString("date"),re.getString("end_addr"),re.getString("end_locationDescribe"),re.getString("end_date")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close();
		}
		return lBeans;
	}
	
	/**
	 * 暂时无用
	 */
	
	@Override
	public LocationBean whosendto(int userid,int otherid) {
		
		// TODO Auto-generated method stub
		ResultSet re=DB.executeQuery(SEARCH,userid,otherid);
		try {
			while (re.next()) {
				LocationBean bean=new LocationBean();
				return bean;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DB.close();
		}
		return null;
	}
	

}

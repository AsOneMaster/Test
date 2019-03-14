package com.dn.web.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dn.web.dao.locationDao;
import com.dn.web.model.LocationBean;
import com.dn.web.tools.DB;


public class LocationService implements locationDao{
	private static String SEARCH="select * from login where userid=?";
	private static String INSERT="insert into location (userid,latitude,longitude,addr,locationDescribe,date) values(?,?,?,?,?,?)";
	@Override
	public int insert(LocationBean bean) {
		// TODO Auto-generated method stub
		int re=DB.executeUpdate(INSERT,bean.getUserId(),bean.getLatitude(),bean.getLongitude(),bean.getAddr(),bean.getLocationDescribe(),bean.getDate());	
		return re;
	}

	@Override
	public List<LocationBean> send(int userid) {
		List<LocationBean> lBeans=new ArrayList<LocationBean>();
		// TODO Auto-generated method stub
		ResultSet re=DB.executeQuery(SEARCH, userid);
		try {
			while (re.next()) {
				LocationBean bean=new LocationBean(re.getInt(0),re.getInt(1),re.getString(2),re.getString(3),null,re.getString(4),re.getString(5),re.getString(6));
				lBeans.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lBeans;
	}
	

}

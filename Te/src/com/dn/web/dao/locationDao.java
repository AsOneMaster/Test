package com.dn.web.dao;

import java.util.List;


import com.dn.web.model.LocationBean;

public interface locationDao {
	
	int insert_start(LocationBean bean);
	int insert_end(LocationBean bean,String date);
	List<LocationBean> SerchEvent(int userid);
	LocationBean whosendto(int userid,int otherid);
}

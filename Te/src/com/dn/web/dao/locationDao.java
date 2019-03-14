package com.dn.web.dao;

import java.util.List;


import com.dn.web.model.LocationBean;

public interface locationDao {
	
	int insert(LocationBean bean);
	
	List<LocationBean> send(int userid);
}

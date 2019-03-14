package com.dn.web.model;



public class LocationBean {
    private int userId;                //本机用户名
    private int otherId;               //监控的用户名
    private String latitude ;             //经度
    private String longitude ;            //纬度
    private String radius ;                //精度
    private String addr;                   //地址
    private String locationDescribe;      //位置描述
    private String date;
    public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	private String speed;
    private String height;

    public LocationBean(){

    }
    public LocationBean(int userId, int otherId, String  latitude, String  longitude,String  radius, String addr, String locationDescribe,String date) {
        this.userId = userId;
        this.otherId=otherId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.addr = addr;
        this.locationDescribe = locationDescribe;
        this.date=date;
    }
    public LocationBean(int userId, String  latitude, String  longitude, String addr, String locationDescribe) {
        this.userId = userId;

        this.latitude = latitude;
        this.longitude = longitude;

        this.addr = addr;
        this.locationDescribe = locationDescribe;

    }
    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String  getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String  getLongitude() {
        return longitude;
    }

    public void setLongitude(String  longitude) {
        this.longitude = longitude;
    }

    public String  getRadius() {
        return radius;
    }

    public void setRadius(String  radius) {
        this.radius = radius;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLocationDescribe() {
        return locationDescribe;
    }

    public void setLocationDescribe(String locationDescribe) {
        this.locationDescribe = locationDescribe;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}

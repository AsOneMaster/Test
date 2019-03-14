package com.dn.web.model;

public class DataBaseBean {
	private int id;		//id
	private String otherId;
    private String userId;                //�û���
    private double latitude ;             //����
    private double longitude ;            //γ��
    private float radius ;                //����  
    private String addr;                   //��ַ
    private String locationDescribe;      //λ����Ϣ
    private String speed;				//�ٶ�
    private String height;				//�߶�
    private String date;			//	ʱ��
    
    public DataBaseBean() {
		super();
	}

	public DataBaseBean(String userId, String otherId, double latitude, double longitude, float radius, String addr,
			String locationDescribe, String speed, String height, String date) {
		super();
		this.userId = userId;
		this.otherId=otherId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.addr = addr;
		this.locationDescribe = locationDescribe;
		this.speed = speed;
		this.height = height;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
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

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
}

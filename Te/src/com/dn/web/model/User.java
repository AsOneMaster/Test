package com.dn.web.model;

import java.util.Date;

public class User {


    private String name;
    private String pass;
    private int userid;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String name, String pass) {
        super();

        this.name = name;
        this.pass = pass;

    }
    public User(int userid,String name, String pass) {
        super();
        this.userid=userid;
        this.name = name;
        this.pass = pass;

    }

    public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

}


package com.dn.web.model;

public class School {
   private int s_id;
   private String s_name;
public int getS_id() {
	return s_id;
}
public void setS_id(int s_id) {
	this.s_id = s_id;
}
public String getS_name() {
	return s_name;
}
public void setS_name(String s_name) {
	this.s_name = s_name;
}
public School(int s_id, String s_name) {
	this.s_id = s_id;
	this.s_name = s_name;
}
public School() {
}
   
}

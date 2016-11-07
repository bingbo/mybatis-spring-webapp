package com.ibingbo.models;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

	private int id;
	private String name;
	private Date login_time;
	private Date create_time;
	private int buc_userid;
	private int empid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getBuc_userid() {
		return buc_userid;
	}
	public void setBuc_userid(int buc_userid) {
		this.buc_userid = buc_userid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	
	
}

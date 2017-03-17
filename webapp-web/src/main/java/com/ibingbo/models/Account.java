package com.ibingbo.models;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account implements Serializable {

	private Integer id;
	private String name;
	private Date login_time;
	private Date create_time;
	private Integer buc_userid;
	private Integer empid;
	public Account(Integer id, String name, Date login_time, Date create_time, Integer buc_userid, Integer empid) {
		super();
		this.id = id;
		this.name = name;
		this.login_time = login_time;
		this.create_time = create_time;
		this.buc_userid = buc_userid;
		this.empid = empid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getBuc_userid() {
		return buc_userid;
	}
	public void setBuc_userid(Integer buc_userid) {
		this.buc_userid = buc_userid;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	
	
	
	
}

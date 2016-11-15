package com.ibingbo.models.DO;

import java.util.Date;

public class StudentDO {

	private Integer id;
	private String name;
	private String password;
	private Date create_time;
	
	public StudentDO(Integer id, String name, String password, Date create_time) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.create_time = create_time;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getCreate_time() {
		return create_time;
	}
	
	
	
}

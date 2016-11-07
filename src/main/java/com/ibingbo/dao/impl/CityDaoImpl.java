package com.ibingbo.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ibingbo.dao.CityDao;
import com.ibingbo.models.City;

public class CityDaoImpl extends SqlSessionDaoSupport implements CityDao {

	@Override
	public List<City> getCities() {
		// TODO Auto-generated method stub
		return (List)this.getSqlSession().selectList("com.ibingbo.mappers.city.getCityList");
	}

}

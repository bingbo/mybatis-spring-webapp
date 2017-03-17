package com.ibingbo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibingbo.dao.CityDao;
import com.ibingbo.models.City;

@Repository
public class CityDaoImpl implements CityDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<City> getCities() {
		// TODO Auto-generated method stub
		return (List)this.sqlSession.selectList("com.ibingbo.mappers.city.getCityList");
	}

}

package com.ibingbo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibingbo.dao.CityDao;
import com.ibingbo.models.City;
import com.ibingbo.services.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCities() {
		// TODO Auto-generated method stub
		return this.cityDao.getCities();
	}

}

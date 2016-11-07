package com.ibingbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibingbo.models.City;
import com.ibingbo.services.CityService;

@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<City> list(){
		return this.cityService.getCities();
		
	}

}

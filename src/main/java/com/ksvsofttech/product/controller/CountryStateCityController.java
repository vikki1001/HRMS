package com.ksvsofttech.product.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksvsofttech.product.dao.CountryStateCityDao;

@Controller
public class CountryStateCityController {

	@Autowired
	private CountryStateCityDao dao;

	@GetMapping("/country")
	public @ResponseBody Map<Integer, String> getCountry(Model model) throws Exception {
		Map<Integer, String> countryMap = dao.getCountriesMap();
		try {
			return countryMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryMap;
	}

	@GetMapping("/state")
	public @ResponseBody Map<Integer, String> getState(Model model, Integer countryId) throws Exception {
		Map<Integer, String> statesMap = dao.getStateMap(countryId);
		try {
			return statesMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statesMap;
	}

	@GetMapping("/city")
	public @ResponseBody Map<Integer, String> getCity(Model model, Integer stateId) throws Exception {
		Map<Integer, String> citiesMap = dao.getCitiesMap(stateId);
		try {
			return citiesMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return citiesMap;
	}
}

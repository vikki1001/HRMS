package com.ksvsofttech.product.dao;

import java.util.Map;

public interface CountryStateCityDao {

	public Map<Integer, String> getCountriesMap() throws Exception;

	public Map<Integer, String> getStateMap(Integer countryId) throws Exception;

	public Map<Integer, String> getCitiesMap(Integer stateId) throws Exception;

}

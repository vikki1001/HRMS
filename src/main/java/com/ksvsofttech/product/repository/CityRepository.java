package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.CityModel;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Integer> {

	@Query(value = "SELECT * FROM CityMaster WHERE stateId = :cityId", nativeQuery = true)
	public List<CityModel> getCityByState(@Param("cityId") Integer cityId);
}

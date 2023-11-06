package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.StateModel;

@Repository
public interface StateRepository extends JpaRepository<StateModel, Integer> {

	@Query(value = "SELECT * FROM StateMaster WHERE countryId = :stateId", nativeQuery = true)
	public List<StateModel> getStateByCountry(@Param("stateId") Integer stateId);
}

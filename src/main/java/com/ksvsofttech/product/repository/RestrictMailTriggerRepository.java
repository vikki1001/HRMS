package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.RestrictMailTrigger;

@Repository
public interface RestrictMailTriggerRepository extends JpaRepository<RestrictMailTrigger, Long> {

	@Query(value = "SELECT userId FROM RestrictMailTrigger WHERE isActive = '1'", nativeQuery = true)
	public List<String> getRestrictEmployees();

}

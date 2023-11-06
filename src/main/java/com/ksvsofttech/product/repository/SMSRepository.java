package com.ksvsofttech.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.SMSTemplate;

@Repository
public interface SMSRepository extends JpaRepository<SMSTemplate, Long> {
	
	@Query("SELECT e FROM SMSTemplate e WHERE e.id = :id")
	public Optional<SMSTemplate> findByid(Long id);

	@Query("SELECT u FROM SMSTemplate u WHERE u.isActive=1")
	public List<SMSTemplate> getIsActive();

	@Query("SELECT u FROM SMSTemplate u WHERE u.isActive=0")
	public List<SMSTemplate> getInActive();
	 
}

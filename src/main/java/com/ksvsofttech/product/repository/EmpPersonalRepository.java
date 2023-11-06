package com.ksvsofttech.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.EmpPersonalDetails;

@Repository
public interface EmpPersonalRepository extends JpaRepository<EmpPersonalDetails, Long> {

	@Query(value = "SELECT * FROM EmpPersonalDetails WHERE  day(birthDate) > day(CURRENT_DATE) AND month(birthDate) = month(CURRENT_DATE) ORDER BY birthDate ASC", nativeQuery = true)
	public List<EmpPersonalDetails> getUpcomingEmpBirthday();
	
	@Query(value = "SELECT * FROM EmpPersonalDetails WHERE  day(birthDate) = day(CURRENT_DATE) AND month(birthDate) = month(CURRENT_DATE) ORDER BY birthDate ASC", nativeQuery = true)
	public List<EmpPersonalDetails> getEmpBirthdayToday();
	
	@Query(value = "SELECT * FROM EmpPersonalDetails WHERE maritalStatus = 'Married' AND employeeid = :empId", nativeQuery = true)
	public EmpPersonalDetails getMarriedOnly(@Param(value = "empId") String empId);
	
	@Query(value = "SELECT employeeid FROM EmpPersonalDetails WHERE maritalStatus = 'Married'",nativeQuery = true)
	public List<String> getMarriedEmployee();
}

package com.ksvsofttech.product.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.PayrollMst;

@Repository
@Transactional
public interface PayrollRepository extends JpaRepository<PayrollMst, Long> {

//	@Query(value = "SELECT * FROM PayrollMst WHERE MONTH(date) = MONTH(CURDATE()) AND YEAR(date) = YEAR(CURDATE()) AND empId = :empId AND isActive='1'", nativeQuery = true)
//	public List<PayrollMst> getPayrollSlipByMonthAndYear(String empId);
	
	@Query(value = "SELECT * FROM PayrollMst WHERE MONTH(date) = :month AND YEAR(date) = :year AND empId = :empId AND isActive='1'", nativeQuery = true)
	public List<PayrollMst> getPayrollSlipByMonthAndYear(String month, String year, String empId);
	
	
}

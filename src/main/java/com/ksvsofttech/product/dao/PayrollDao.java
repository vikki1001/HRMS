package com.ksvsofttech.product.dao;

import java.util.List;

import com.ksvsofttech.product.entities.PayrollMst;

public interface PayrollDao {

	public void saveAll(List<PayrollMst> payrollMst) throws Exception;
	
	public List<PayrollMst> getPayrollSlipByMonthAndYear(String month, String year, String empId) throws Exception;
	
	public List<PayrollMst> findAll() throws Exception;
}

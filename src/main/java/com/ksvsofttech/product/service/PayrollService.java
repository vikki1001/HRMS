package com.ksvsofttech.product.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ksvsofttech.product.entities.PayrollMst;

public interface PayrollService {

	public void saveAll(MultipartFile files) throws Exception;
	
	public List<PayrollMst> getPayrollSlipByMonthAndYear(String month, String year, String empId) throws Exception;

	public List<PayrollMst> findAll() throws Exception;

	public ByteArrayInputStream exportPayroll(List<PayrollMst> payrollMst) throws Exception;

}

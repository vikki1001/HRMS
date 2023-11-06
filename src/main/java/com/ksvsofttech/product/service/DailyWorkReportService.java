package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.DailyWorkReport;

public interface DailyWorkReportService {

	public List<DailyWorkReport> getIsActive(String empId) throws Exception;
	
	public DailyWorkReport findByFullName(String fullName) throws Exception;
	
	public DailyWorkReport saveWorkReport(DailyWorkReport dailyWorkReport) throws Exception;

	public DailyWorkReport cancelWorkReport(DailyWorkReport dailyWorkReport) throws Exception;

	public DailyWorkReport getWorkReportById(Long id) throws Exception;

	public List<DailyWorkReport> getInActive(String empId)  throws Exception;

	public List<DailyWorkReport> getTotalWorkReport() throws Exception;

	public long getAllWorkReport() throws Exception;

}

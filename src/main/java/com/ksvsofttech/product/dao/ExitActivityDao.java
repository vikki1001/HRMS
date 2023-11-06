package com.ksvsofttech.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.entities.ExitActivity;

@Repository
public interface ExitActivityDao {

	public List<ExitActivity> getAllEmp() throws Exception;

	public ExitActivity saveExitActivity(ExitActivity exitActivity) throws Exception;

	public ExitActivity cancelById(ExitActivity exitActivity) throws Exception;

	public List<ExitActivity> isActiveExitActivity(String empId) throws Exception;

	public List<ExitActivity> cancelExitActivity(String empId) throws Exception;

	public List<ExitActivity> getExitActivityById(String empId) throws Exception;

	public List<ExitActivity> getEmpWithManger(String empId) throws Exception;

	public void acceptStatus(String status, String flag, Long id) throws Exception;

	public List<ExitActivity> acceptExitActivityById(Long id) throws Exception;
	
	public List<ExitActivity> getEmpWithMangerWithPending(String userId) throws Exception;

	public List<ExitActivity> getTotalExitActivity() throws Exception;
	
	public long getAllExitActivity() throws Exception;

	public ExitActivity notificationRead(ExitActivity exitActivity) throws Exception;
}

package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpWorkDetails;

public interface EmpRegistartionDao {

	public EmpBasicDetails getEmpById(String empId) throws Exception;

	public EmpBasicDetails saveBasicDetails(EmpBasicDetails empBasicDetails) throws Exception;

	public Optional<EmpBasicDetails> findEmpByEmail(String coEmailId) throws Exception;

	public boolean userExists(String coEmailId) throws Exception;

	public Optional<EmpBasicDetails> getImageById(String empId) throws Exception;

	public List<EmpBasicDetails> getFindAllEmpRegList() throws Exception;
		
	public List<EmpBasicDetails> getIsActiveEmpRegList() throws Exception;
	
	public List<EmpBasicDetails> getInActiveEmpRegList() throws Exception;

	public EmpBasicDetails deactiveEmp(EmpBasicDetails empBasicDetails) throws Exception;

	public EmpBasicDetails activateEmp(EmpBasicDetails empBasicDetails) throws Exception;

	public EmpBasicDetails getCurrentUser(String empId) throws Exception;

	public EmpBasicDetails getImage(String empId) throws Exception;

	public List<EmpWorkDetails> getEmpWithManger(String userId) throws Exception;

	public List<String> getEmpIdWithIsActive() throws Exception;
	
	public long getAllEmployees() throws Exception;

	public List<String> getUniqueTenantId() throws Exception;

	public List<EmpBasicDetails> listOfCurrentUser(String empId) throws Exception;
	
	public void uploadImage(byte[] imageData, String empId) throws Exception;

	public List<String> getUniqueDepId() throws Exception;

	public List<String> getAllEmpId(String empId) throws Exception;

	public List<EmpWorkDetails> getAllEmpJoiningDate(List<String> empIds) throws Exception;

	public EmpBasicDetails getKRAWithDepIdAndEmpId(String depId, String reportingManager) throws Exception;

	public Optional<EmpBasicDetails> findById(String empId) throws Exception;
}

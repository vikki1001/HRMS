package com.ksvsofttech.product.service;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.DepartmentMst;

public interface DepartmentService {

	
	  public DepartmentMst deactiveDepartment(DepartmentMst departmentMst) throws Exception;
	 
	  public DepartmentMst activateDepartment(DepartmentMst departmentMst) throws Exception;
	  
	  public List<DepartmentMst> getIsActive() throws Exception;
	  
	  public DepartmentMst getByDepartmentId(Long departmentId)throws Exception;
	  
	  public List<DepartmentMst> getInActive() throws Exception;
	 
	  public void save(DepartmentMst department) throws Exception;
	  
	  public Optional<DepartmentMst> findDeptByDeptName(String departmentName) throws Exception;
	  
	  /* For Duplicate Role Code Validation */
	  public boolean departmentExists(String departmentCode) throws Exception;

}

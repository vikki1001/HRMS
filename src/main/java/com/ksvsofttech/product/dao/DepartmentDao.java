package com.ksvsofttech.product.dao;

import java.util.List;
import java.util.Optional;

import com.ksvsofttech.product.entities.DepartmentMst;

public interface DepartmentDao {

	  public DepartmentMst deactiveDepartment(DepartmentMst departmentMst) throws Exception;
	 
      public DepartmentMst activateDepartment(DepartmentMst departmentMst) throws Exception;
      	  
	  public List<DepartmentMst> getIsActive() throws Exception;
	  
	  public DepartmentMst findByDepartmentId(Long departmentId)throws Exception;
	  
	  public List<DepartmentMst> getInActive() throws Exception;
	 
	  public DepartmentMst save(DepartmentMst department) throws Exception;
	  
	  public Optional<DepartmentMst> findDeptByDeptName(String departmentName) throws Exception;
	  
	  /* For Duplicate Role Code Validation */
	  public boolean departmentExists(String departmentName) throws Exception;
}

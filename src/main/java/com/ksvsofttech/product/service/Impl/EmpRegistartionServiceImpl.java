package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.EmpRegistartionDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpWorkDetails;
import com.ksvsofttech.product.service.EmpRegistartionService;

@Service
public class EmpRegistartionServiceImpl implements EmpRegistartionService {
	private static final Logger LOG = LogManager.getLogger(EmpRegistartionServiceImpl.class);

	@Autowired
	private EmpRegistartionDao empRegistartionDao;

	@Override
	public List<EmpBasicDetails> getFindAllEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails;
		try {
			empBasicDetails = empRegistartionDao.getFindAllEmpRegList();
		} catch (Exception e) {
			LOG.error("------Error occur while display findAll employee registartion list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No findAll employee register record exist......");
		}
		return empBasicDetails;
	}

	/* Display List of IsActive Employee Registration list */
	@Override
	public List<EmpBasicDetails> getIsActiveEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails;
		try {
			empBasicDetails = empRegistartionDao.getIsActiveEmpRegList();
		} catch (Exception e) {
			LOG.error("------Error occur while display isactive employee registartion list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive employee register record exist......");
		}
		return empBasicDetails;
	}

	/* Display List of InActive Employee Registartion list */
	@Override
	public List<EmpBasicDetails> getInActiveEmpRegList() throws Exception {
		List<EmpBasicDetails> empBasicDetails;
		try {
			empBasicDetails = empRegistartionDao.getInActiveEmpRegList();
		} catch (Exception e) {
			LOG.error("------Error occur while display inactive employee registartion list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive employee register record exist......");
		}
		return empBasicDetails;
	}

	/* Find Emp By Id */
	@Override
	public EmpBasicDetails getEmpById(String empId) throws Exception {
		EmpBasicDetails empBasicDetails = new EmpBasicDetails();
		try {
			empBasicDetails = empRegistartionDao.getEmpById(empId);
		} catch (Exception e) {
			LOG.error("Error while Emp not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Role not found for id :: " + empId);
		}
		return empBasicDetails;
	}

	@Override
	public Optional<EmpBasicDetails> findEmpByEmail(String coEmailId) throws Exception {
		return empRegistartionDao.findEmpByEmail(coEmailId);
	}

	@Override
	public boolean userExists(String coEmailId) throws Exception {
		return empRegistartionDao.userExists(coEmailId);
	}

	/* Save Record */
	@Override
	public EmpBasicDetails saveBasicDetails(EmpBasicDetails empBasicDetails) throws Exception {
		try {
			empRegistartionDao.saveBasicDetails(empBasicDetails);
		} catch (Exception e) {
			LOG.error("------Error occur while save basic record------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee basic record not save ......");
		}
		return empBasicDetails;
	}

	@Override
	public Optional<EmpBasicDetails> getImageById(String empId) throws Exception {
		return empRegistartionDao.findEmpByEmail(empId);
	}

	/* For Deactivate Register Employee */
	@Override
	public EmpBasicDetails deactiveEmp(EmpBasicDetails empBasicDetails) throws Exception {
		try {
			empRegistartionDao.deactiveEmp(empBasicDetails);
		} catch (Exception e) {
			LOG.error("------Error occur while deactive employee------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee deactivate......");
		}
		return empBasicDetails;
	}

	/* For Deactivate Register Employee */
	@Override
	public EmpBasicDetails activateEmp(EmpBasicDetails empBasicDetails) throws Exception {
		try {
			empRegistartionDao.activateEmp(empBasicDetails);
		} catch (Exception e) {
			LOG.error("------Error occur while deactive employee------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee activate......");
		}
		return empBasicDetails;
	}

	/* Get Current Active Employee Data by empId */
	@Override
	public EmpBasicDetails getCurrentUser(String empId) throws Exception {
		try {
			return this.empRegistartionDao.getCurrentUser(empId);
		} catch (Exception e) {
			LOG.error("------Error occur while disply employee data------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee not found......");
		}
	}

	/* Get Employee Image by empId */
	@Override
	public EmpBasicDetails getImage(String empId) throws Exception {
		try {
			return this.empRegistartionDao.getImage(empId);
		} catch (Exception e) {
			LOG.error("------Error occur while disply employee Image------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Employee not found......");
		}
	}

	@Override
	public List<EmpWorkDetails> getEmpWithManger(String userId) throws Exception {
		List<EmpWorkDetails> empWorkDetails = empRegistartionDao.getEmpWithManger(userId);
		try {
			if (Objects.nonNull(empWorkDetails)) {
				return empWorkDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while disply records to manager------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<String> getEmpIdWithIsActive() throws Exception {
		List<String> listOfEmp = empRegistartionDao.getEmpIdWithIsActive();
		try {
			if (Objects.nonNull(listOfEmp)) {
				return listOfEmp;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get list of employee ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No data found......");
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllEmployees() throws Exception {
		try {
			return empRegistartionDao.getAllEmployees();
		} catch (Exception e) {
			LOG.error("Error occuring while get all employee in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... ");
		}
	}

	@Override
	public List<String> getUniqueTenantId() throws Exception {
		List<String> listOfUniqueTenantId = empRegistartionDao.getUniqueTenantId();
		try {
			if (Objects.nonNull(listOfUniqueTenantId)) {
				return listOfUniqueTenantId;
			} else {
				System.out.println("list Of Unique Tenant Id is null");
			}
		} catch (Exception e) {
			LOG.error("Error occuring while ge Unique Tenant Id  " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpBasicDetails> listOfCurrentUser(String empId) throws Exception {
		List<EmpBasicDetails> empBasicDetails = empRegistartionDao.listOfCurrentUser(empId);
		try {
			if (!empBasicDetails.isEmpty()) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while display all data from list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return empBasicDetails;
	}

	@Override
	public void uploadImage(byte[] imageData, String empId) throws Exception {
		try {
			empRegistartionDao.uploadImage(imageData, empId);
		} catch (Exception e) {
			LOG.error("------Error occur while upload image ------" + ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public List<String> getUniqueDepId() throws Exception {
			List<String> empsList = empRegistartionDao.getUniqueDepId();
			try {
				if (Objects.nonNull(empsList)) {
					return empsList;
				}
		} catch (Exception e) {
			LOG.error("------Error occur while get unique depId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<String> getAllEmpId(String empId) throws Exception {
		List<String> empsList = empRegistartionDao.getAllEmpId(empId);
		try {
			if (Objects.nonNull(empsList)) {
				return empsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get All EmpId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public List<EmpWorkDetails> getAllEmpJoiningDate(List<String> empIds) throws Exception {
		List<EmpWorkDetails> empsList = empRegistartionDao.getAllEmpJoiningDate(empIds);
		try {
			if (Objects.nonNull(empsList)) {
				return empsList;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get All Emp JoiningDate ------" + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public EmpBasicDetails getKRAWithDepIdAndEmpId(String depId, String reportingManager) throws Exception {
		EmpBasicDetails empBasicDetails = empRegistartionDao.getKRAWithDepIdAndEmpId(depId, reportingManager);
		try {
			if (Objects.nonNull(empBasicDetails)) {
				return empBasicDetails;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get KRA With DepId And EmpId ------" + ExceptionUtils.getStackTrace(e));
		}
		return new EmpBasicDetails();
	}

	@Override
	public Optional<EmpBasicDetails> findById(String empId) throws Exception {
		Optional<EmpBasicDetails> optional = empRegistartionDao.findById(empId);
		try {
			if (optional.isPresent()) {
				return optional;
			}
		} catch (Exception e) {
			LOG.error("------Error occur while get find by id ------" + ExceptionUtils.getStackTrace(e));
		}
		return Optional.empty();
	}
}
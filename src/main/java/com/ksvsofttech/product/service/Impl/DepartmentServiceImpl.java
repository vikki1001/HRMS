package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.DepartmentDao;
import com.ksvsofttech.product.entities.DepartmentMst;
import com.ksvsofttech.product.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public void save(DepartmentMst departmentMst) throws Exception {
		try {
			this.departmentDao.save(departmentMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save & update......" + departmentMst);
		}
	}

	@Override
	public List<DepartmentMst> getIsActive() throws Exception {
		List<DepartmentMst> departmentMst = new ArrayList<>();
		try {
			departmentMst = departmentDao.getIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......" + departmentMst);
		}
		return departmentMst;
	}

	@Override
	public List<DepartmentMst> getInActive() throws Exception {
		List<DepartmentMst> departmentMst = new ArrayList<>();
		try {
			departmentMst = departmentDao.getInActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......" + departmentMst);
		}
		return departmentMst;
	}

	@Override
	public DepartmentMst getByDepartmentId(Long departmentId) throws Exception {
		DepartmentMst departmentMst = new DepartmentMst();
		try {
			departmentMst = departmentDao.findByDepartmentId(departmentId);
		} catch (Exception e) {
			LOGGER.error("------Error while role not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No role exist for given id......" + departmentMst);
		}
		return departmentMst;
	}

	@Override
	public DepartmentMst deactiveDepartment(DepartmentMst departmentMst) throws Exception {
		try {
			departmentDao.deactiveDepartment(departmentMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role deactivate" + departmentMst);
		}
		return departmentMst;
	}

	@Override
	public DepartmentMst activateDepartment(DepartmentMst departmentMst) throws Exception {
		try {
			departmentDao.activateDepartment(departmentMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while active role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role activate" + departmentMst);
		}
		return departmentMst;
	}

	@Override
	public Optional<DepartmentMst> findDeptByDeptName(String departmentName) throws Exception {
		try {
			return departmentDao.findDeptByDeptName(departmentName);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find duplicate department ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department exists......");
		}
	}

	@Override
	public boolean departmentExists(String departmentName) throws Exception {
		try {
			return findDeptByDeptName(departmentName).isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while find department code ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department code exists......");
		}

	}
}

package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.DepartmentDao;
import com.ksvsofttech.product.entities.DepartmentMst;
import com.ksvsofttech.product.repository.DepartmentRepository;
import com.ksvsofttech.product.repository.RoleRepository;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	private static final Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class);

	@Autowired
	public DepartmentRepository departmentRepository;

	@Autowired
	public RoleRepository roleRepository;

	@Override
	public List<DepartmentMst> getIsActive() throws Exception {
		List<DepartmentMst> departmentMst = departmentRepository.getIsActive();
		try {
			if (!departmentMst.isEmpty()) {
				return departmentMst;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display active department list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<DepartmentMst> getInActive() throws Exception {
		List<DepartmentMst> departmentMst = departmentRepository.getInActive();
		try {
			if (!departmentMst.isEmpty()) {
				return departmentMst;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display inactive department list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department record exist ......");
		}
		return new ArrayList<>();
	}

	@Override
	public DepartmentMst save(DepartmentMst departmentMst) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			Optional<DepartmentMst> departments = departmentRepository
					.findByDepartmentId(departmentMst.getDepartmentId());
			if (departments.isPresent()) {
				DepartmentMst newdepartment = departments.get();
				newdepartment.setDepartmentId(departmentMst.getDepartmentId());
				newdepartment.setDepartmentName(departmentMst.getDepartmentName());
				newdepartment.setRoleId(departmentMst.getRoleId());
				newdepartment.setDepartmentDesc(departmentMst.getDepartmentDesc());
				newdepartment.setLastModifiedDate(new Date());
				newdepartment.setLastModifiedBy(loginId);
				return departmentRepository.save(newdepartment);
			} else {
				departmentMst.setIsActive("1");
				departmentMst.setIsAdminFlag(0);
				departmentMst.setVersion(0);
				departmentMst.setCreatedDate(new Date());
				departmentMst.setCreatedBy(loginId);
				// department.setTenantId("1");
				return this.departmentRepository.save(departmentMst);
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update department------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department save or update......");
		}
	}

	@Override
	public DepartmentMst findByDepartmentId(Long departmentId) throws Exception {
		Optional<DepartmentMst> optional = departmentRepository.findByDepartmentId(departmentId);
		DepartmentMst departmentMst = null;
		try {
			if (optional.isPresent()) {
				departmentMst = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("Error occur department not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("department not found for id :: " + departmentId);
		}
		return departmentMst;
	}

	@Override
	public DepartmentMst deactiveDepartment(DepartmentMst departmentMst) throws Exception {
		try {
			Optional<DepartmentMst> department2 = departmentRepository
					.findByDepartmentId(departmentMst.getDepartmentId());
			if (department2.isPresent()) {

				DepartmentMst newdepartment = department2.get();
				newdepartment.setIsActive(departmentMst.getIsActive());
				newdepartment.setIsActive("0");

				departmentRepository.save(newdepartment);
			} else {
				return departmentMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactivate department------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department deactivate......");
		}
		return departmentMst;
	}

	@Override
	public DepartmentMst activateDepartment(DepartmentMst departmentMst) throws Exception {
		try {
			Optional<DepartmentMst> department2 = departmentRepository
					.findByDepartmentId(departmentMst.getDepartmentId());
			if (department2.isPresent()) {

				DepartmentMst newdepartment = department2.get();
				newdepartment.setIsActive(departmentMst.getIsActive());
				newdepartment.setIsActive("1");

				departmentRepository.save(newdepartment);
			} else {
				return departmentMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate department------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No department activate......");
		}
		return departmentMst;
	}

	@Override
	public Optional<DepartmentMst> findDeptByDeptName(String departmentName) throws Exception {
		try {
			return departmentRepository.findDeptByDeptName(departmentName);
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

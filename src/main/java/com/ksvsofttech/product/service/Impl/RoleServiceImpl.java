package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.RoleDao;
import com.ksvsofttech.product.entities.RoleMst;
import com.ksvsofttech.product.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	/* List Of IsActive Roles */
	public List<RoleMst> getIsActive() throws Exception {
		List<RoleMst> roleList = new ArrayList<>();
		try {
			roleList = roleDao.getIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive role record exist......");
		}
		return roleList;
	}

	/* List Of Inactive Roles */
	public List<RoleMst> getInActive() throws Exception {
		List<RoleMst> roleList = new ArrayList<>();
		try {
			roleList = roleDao.getInActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive role record exist......");
		}
		return roleList;
	}

	/* For Save & Update Role */
	public void save(RoleMst role) throws Exception {
		try {
			this.roleDao.save(role);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save & update......" + role);
		}
	}

	/* Find Role By Id */
	public RoleMst getRoleById(long roleId) throws Exception {
		RoleMst roleMst = new RoleMst();
		try {
			roleMst = roleDao.getRoleById(roleId);
		} catch (Exception e) {
			LOGGER.error("------Error while role not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No role exist for given id......" + roleId);
		}
		return roleMst;
	}

	/* For Deactivate Role */
	@Override
	public RoleMst deactiveRole(RoleMst roleMst) throws Exception {
		try {
			roleMst = roleDao.deactiveRole(roleMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role deactivate" + roleMst.getRoleId());
		}
		return roleMst;
	}

	/* For Activate Role */
	@Override
	public RoleMst activateRole(RoleMst roleMst) throws Exception {
		try {
			roleMst = roleDao.activateRole(roleMst);
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role-----" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No Role activate" + roleMst.getRoleId());
		}
		return roleMst;
	}

	/* For Duplicate Role Code Validation */
	@Override
	public Optional<RoleMst> findRoleByRoleCode(String roleCode) throws Exception {
		try {
			return roleDao.findRoleByRoleCode(roleCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find duplicate role ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role exists......");
		}
	}

	@Override
	public boolean roleExists(String roleCode) throws Exception {
		try {
			return findRoleByRoleCode(roleCode).isPresent();
		} catch (Exception e) {
			LOGGER.error("------Error occur while find role code ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role code exists......");
		}

	}
}

package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.RoleMenuActionAccessDao;
import com.ksvsofttech.product.entities.RoleMenuActionAccess;
import com.ksvsofttech.product.repository.RoleMenuActionAccessRepository;

@Repository
public class RoleMenuActionAccessDaoImpl implements RoleMenuActionAccessDao {
	private static final Logger LOGGER = LogManager.getLogger(RoleMenuActionAccessDaoImpl.class);

	@Autowired
	RoleMenuActionAccessRepository roleMenuActionAccessRepository;

	public List<RoleMenuActionAccess> getMenuListByModuleRoleMap(String tenantId, String roleCode, String isActive)
			throws Exception {
		List<RoleMenuActionAccess> roleMenuActionAccess;
		try {
			roleMenuActionAccess = roleMenuActionAccessRepository.getMenuListByRoleMap(tenantId, roleCode, isActive);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find menu list ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu list find ......");
		}
		return roleMenuActionAccess;
	}

	public List<RoleMenuActionAccess> getAllMenusByRoleCode(String roleCode) throws Exception {
		List<RoleMenuActionAccess> roleMenuActionAccess = roleMenuActionAccessRepository.getMenuByRoleCode(roleCode);
		try {
			if (roleMenuActionAccess.size() > 0) {
				return roleMenuActionAccess;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while find menus by role code------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menus find by role code ......");
		}
		return new ArrayList<>();
	}

	/* List Of IsActive RoleMenuActionAccess */
	public List<RoleMenuActionAccess> getRoleMenuActionAccessListByRoleCode(String roleCode) throws Exception {
		List<RoleMenuActionAccess> roleMenuActionAccessList = roleMenuActionAccessRepository
				.getRoleMenuActionAccessListByRoleCode(roleCode);
		try {
			if (roleMenuActionAccessList.size() > 0) {
				return roleMenuActionAccessList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role menu action access list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role menu action access record exist ......");
		}
		return new ArrayList<>();
	}

	public void saveMenus(List<RoleMenuActionAccess> roleMenuActionAccess) throws Exception {
		try {
			this.roleMenuActionAccessRepository.saveAll(roleMenuActionAccess);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save menus------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menus save ......");
		}
	}
}

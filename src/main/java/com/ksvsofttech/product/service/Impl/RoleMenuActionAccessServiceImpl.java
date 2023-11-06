package com.ksvsofttech.product.service.Impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.RoleMenuActionAccessDao;
import com.ksvsofttech.product.entities.RoleMenuActionAccess;
import com.ksvsofttech.product.service.RoleMenuActionAccessService;

@Service
public class RoleMenuActionAccessServiceImpl implements RoleMenuActionAccessService {
	private static final Logger LOGGER = LogManager.getLogger(RoleMenuActionAccessServiceImpl.class);

	@Autowired
	private RoleMenuActionAccessDao roleMenuActionAccessDao;

	@Override
	public List<RoleMenuActionAccess> getMenuListByModuleRoleMap(String TenantId, String roleCode, String isActive)
			throws Exception {
		try {
			return roleMenuActionAccessDao.getMenuListByModuleRoleMap(TenantId, roleCode, isActive);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display menu list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu record exist ......");
		}
	}

	@Override
	public List<RoleMenuActionAccess> getAllMenusByRoleCode(String roleCode) throws Exception {
		try {
			return roleMenuActionAccessDao.getAllMenusByRoleCode(roleCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display menu by role code------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu by role code record exist ......");
		}
	}

	public List<RoleMenuActionAccess> getRoleMenuActionAccessListByRoleCode(String roleCode) throws Exception {
		List<RoleMenuActionAccess> roleMenuActionAccessList;
		try {
			roleMenuActionAccessList = roleMenuActionAccessDao.getRoleMenuActionAccessListByRoleCode(roleCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role menu action access list------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No role menu action access record exist ......");
		}
		return roleMenuActionAccessList;
	}

	public void saveMenus(List<RoleMenuActionAccess> roleMenuActionAccess) throws Exception {
		try {
			this.roleMenuActionAccessDao.saveMenus(roleMenuActionAccess);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save menus------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menus save ......");
		}
	}
}

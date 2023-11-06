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

import com.ksvsofttech.product.dao.RoleDao;
import com.ksvsofttech.product.entities.RoleMst;
import com.ksvsofttech.product.repository.RoleRepository;

@Repository
public class RoleDaoImpl implements RoleDao {
	private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);

	@Autowired
	RoleRepository roleRepository;

	/* List Of IsActive Roles */
	public List<RoleMst> getIsActive() throws Exception {
		List<RoleMst> roleList = roleRepository.getIsActive();
		try {
			if (!roleList.isEmpty()) {
				return roleList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display isactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role record exist ......");
		}
		return new ArrayList<>();
	}

	/* List Of Inactive Roles */
	public List<RoleMst> getInActive() throws Exception {
		List<RoleMst> roleList = roleRepository.getInActive();
		try {
			if (!roleList.isEmpty()) {
				return roleList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display inactive role list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive role record exist......");
		}
		return new ArrayList<>();
	}

	/* For Save & Update Role */
	public RoleMst save(RoleMst roleMst) throws Exception {
		String loginId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			loginId = authentication.getName();
			if (roleMst.getRoleId() == null) {
				roleMst.setRoleCode(roleMst.getRoleCode().toUpperCase());
				roleMst.setIsActive("1");
				roleMst.setIsAdminFlag(0);
				roleMst.setVersion(0);
				roleMst.setCreatedDate(new Date());
				roleMst.setCreatedBy(loginId);
				roleMst = roleRepository.save(roleMst);
			} else {				
				Optional<RoleMst> role = roleRepository.findById(roleMst.getRoleId());
				if (role.isPresent()) {
					RoleMst newRole = role.get();
					newRole.setRoleName(roleMst.getRoleName());
					newRole.setLevel(roleMst.getLevel());
					newRole.setIsHeadRoleYn(roleMst.getIsHeadRoleYn());
					newRole.setLastModifiedDate(new Date());
					newRole.setLastModifiedBy(loginId);
					roleRepository.save(newRole);
				} else {
					return roleMst;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No role save or update......");
		}
		return roleMst;
	}

	/* Find Role By Id */
	public RoleMst getRoleById(long roleId) throws Exception {
		Optional<RoleMst> optional = roleRepository.findById(roleId);
		RoleMst roleMst = null;
		try {
			if (optional.isPresent()) {
				roleMst = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("Error while Role not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Role not found for id :: " + roleId);
		}
		return roleMst;
	}

	/* For Deactivate Role */
	@Override
	public RoleMst deactiveRole(RoleMst roleMst) throws Exception {
		try {
			Optional<RoleMst> roleMst2 = roleRepository.findById(roleMst.getRoleId());
			if (roleMst2.isPresent()) {
				RoleMst newrole = roleMst2.get();
				newrole.setIsActive(roleMst.getIsActive());
				newrole.setIsActive("0");

				roleRepository.save(newrole);
				return roleMst;
			} else {
				roleRepository.save(roleMst);
				return roleMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role deactivate......");
		}
	}

	/* For Activate Role */
	@Override
	public RoleMst activateRole(RoleMst roleMst) throws Exception {
		try {
			Optional<RoleMst> roleMst2 = roleRepository.findById(roleMst.getRoleId());
			if (roleMst2.isPresent()) {
				RoleMst newrole = roleMst2.get();
				newrole.setIsActive(roleMst.getIsActive());
				newrole.setIsActive("1");

				roleRepository.save(newrole);
				return roleMst;
			} else {
				roleRepository.save(roleMst);
				return roleMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while activate role------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Role activate......");
		}
	}

	/* For Duplicate Role Code Validation */
	@Override
	public Optional<RoleMst> findRoleByRoleCode(String roleCode) throws Exception {
		try {
			return roleRepository.findRoleByRoleCode(roleCode);
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

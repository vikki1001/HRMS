package com.ksvsofttech.product.service.Impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.MenuDao;
import com.ksvsofttech.product.entities.MenuMst;
import com.ksvsofttech.product.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	private static final Logger LOGGER = LogManager.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<MenuMst> getMenuMstIn(String tenantId, Set<String> menuCodeSet) throws Exception {
		List<MenuMst> menuList;
		try {
			menuList = menuDao.getMenuMstIn(tenantId, menuCodeSet);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display menu ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu found...... ");
		}
		return menuList;
	}

	@Override
	public Map<String, List<MenuMst>> getAccessibleMenu(String tenantId, String languageCode,
			Set<String> userMenuCodeSet) throws Exception {
		Map<String, List<MenuMst>> map;
		try {
			map = menuDao.getAccessibleMenu(tenantId, languageCode, userMenuCodeSet);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display accessible menu ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No accessible menu found...... ");
		}
		return map;
	}

	@Override
	public List<MenuMst> getAllIsActive() throws Exception {
		List<MenuMst> menumst;
		try {
			menumst = menuDao.getAllIsActive();
		} catch (Exception e) {
			LOGGER.error("------Error occur while display active menu list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu record exist ......");
		}
		return menumst;
	}
	
	/* find menu by rolecode */
	@Override
	public List<MenuMst> getMenu(String roleCode) throws Exception {
		List<MenuMst> menumst;
		try {
			menumst = menuDao.getMenu(roleCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while find menu by rolecode------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu record exist ......");
		}
		return menumst;
	}
}

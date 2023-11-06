package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.constant.ProdConstant;
import com.ksvsofttech.product.dao.MenuDao;
import com.ksvsofttech.product.entities.MenuMst;
import com.ksvsofttech.product.repository.MenuRepository;

@Repository
public class MenuDaoImpl implements MenuDao {
	private static final Logger LOGGER = LogManager.getLogger(MenuDaoImpl.class);

	@Autowired
	MenuRepository menuMstRepository;

	@Override
	public List<MenuMst> getMenuMstIn(String tenantId, Set<String> menuCodeSet) throws Exception {
		List<MenuMst> menuList;
		try {
			menuList = menuMstRepository.getMenuMstIn(tenantId, menuCodeSet);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display menu ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu found...... ");
		}
		return menuList;
	}

	@Override
	public Map<String, List<MenuMst>> getAccessibleMenu(String tenantId, String languageCode,
			Set<String> userMenuCodeSet) throws Exception {
		Map<String, List<MenuMst>> menuAll = new HashMap<>();
		try {
			Set<String> dummySectionCodes = new HashSet<String>();
			Set<String> sectionCodes = new HashSet<>();
			for (String mm : userMenuCodeSet) {
				dummySectionCodes = getSecitonCodeByMenuCode(tenantId, mm, ProdConstant.TRUE);
				if (null == dummySectionCodes) {
					// DO NOTHING
				} else {
					sectionCodes.addAll(dummySectionCodes);
				}

				List<MenuMst> menuList = new ArrayList<MenuMst>();
				List<MenuMst> menuListMain = new ArrayList<MenuMst>();
				if (null != sectionCodes && !sectionCodes.isEmpty()) {
					for (String sectionCode : sectionCodes) {
						menuListMain = new ArrayList<MenuMst>();
						menuList = getMenuListBySubSectionsDemo(tenantId, sectionCode, ProdConstant.TRUE);
						if (null != menuList && !menuList.isEmpty()) {
							String menCode = "";
							for (MenuMst menu1 : menuList) {
								menCode = menu1.getMenuCode().trim();
								if (null == menCode || menCode.trim().isEmpty())
									continue;
								if (userMenuCodeSet.contains(menCode)) {
									menuListMain.add(menu1);
								}
							}
						}
						if (null != menuListMain && !menuListMain.isEmpty())
							menuAll.put(sectionCode, menuListMain);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menuAll;
	}

	public Set<String> getSecitonCodeByMenuCode(String tenantId, String sectionCode, String isActive) {
		List<MenuMst> menuList = menuMstRepository.getMenuMst(tenantId, sectionCode);
		Set<String> sectionNames = new HashSet<>();
		String sectionName = "";
		for (MenuMst menuMst : menuList) {
			sectionName = menuMst.getSectionDisplayName();
			sectionNames.add(sectionName);
		}
		return sectionNames;
	}

	public List<MenuMst> getMenuListBySubSectionsDemo(String tenantId, String sectionCode, String isActive)
			throws Exception {
		List<MenuMst> menuList;
		try {
			menuList = menuMstRepository.getMenuMstBySubSection(tenantId, sectionCode);
		} catch (Exception e) {
			LOGGER.error("------Error occur while display menu list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu list found...... ");
		}
		return menuList;
	}

	public List<MenuMst> getAllIsActive() throws Exception {
		List<MenuMst> menuMst = menuMstRepository.getIsActive();
		try {
			if (!menuMst.isEmpty()) {
				return menuMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display active menu list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu record exist ......");
		}
		return new ArrayList<>();
	}

	/* find menu by rolecode */
	@Override
	public List<MenuMst> getMenu(String roleCode) throws Exception {
		List<MenuMst> menuMst = menuMstRepository.getMenu(roleCode);
		try {
			if (!menuMst.isEmpty()) {
				return menuMst;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while  find menu by rolecode------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No menu record exist ......");
		}
		return new ArrayList<>();
	}
}

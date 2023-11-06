package com.ksvsofttech.product.service.Impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.TenantConfigDao;
import com.ksvsofttech.product.entities.TenantConfig;
import com.ksvsofttech.product.service.TenantConfigService;

@Service
public class TenantConfigServiceImpl implements TenantConfigService {
	private static final Logger LOGGER = LogManager.getLogger(TenantConfigServiceImpl.class);

	@Autowired
	private TenantConfigDao tenantConfigDao;

	/* For Save TenantConfig */
	public void saveTenantConfig(TenantConfig tenantConfig) throws Exception {
		try {
			this.tenantConfigDao.saveTenantConfig(tenantConfig);
		} catch (Exception e) {
			LOGGER.error("Error while Tenant id not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Tenant id not found for id :: " + tenantConfig);
		}
	}

}

package com.ksvsofttech.product.dao.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.TenantConfigDao;
import com.ksvsofttech.product.entities.TenantConfig;
import com.ksvsofttech.product.repository.TenantConfigRepository;

@Repository
public class TenantConfigDaoImpl implements TenantConfigDao {
	private static final Logger LOGGER = LogManager.getLogger(TenantConfigDaoImpl.class);

	@Autowired
	TenantConfigRepository tenantConfigRepository;

	public void saveTenantConfig(TenantConfig tenantConfig) throws Exception {
		try {
			this.tenantConfigRepository.save(tenantConfig);
		} catch (Exception e) {
			LOGGER.error("Error while Tenant id not found for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Tenant id not found for id :: " + tenantConfig);
		}
	}

}

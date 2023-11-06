package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.RestrictMailDao;
import com.ksvsofttech.product.repository.RestrictMailTriggerRepository;

@Repository
public class RestrictMailDaoImpl implements RestrictMailDao {
	private static final Logger LOGGER = LogManager.getLogger(RestrictMailDaoImpl.class);

	@Autowired
	private RestrictMailTriggerRepository restrictMailTriggerRepository;

	@Override
	public List<String> getRestrictEmployees() throws Exception {
		List<String> restrictEmpList = restrictMailTriggerRepository.getRestrictEmployees();
		try {
			if (!restrictEmpList.isEmpty()) {
				return restrictEmpList;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while get all restrict employee id ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ......");
		}
		return new ArrayList<>();
	}
}

package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.RestrictMailDao;
import com.ksvsofttech.product.service.RestrictMailService;

@Service
public class RestrictMailServiceImpl implements RestrictMailService {
	private static final Logger LOGGER = LogManager.getLogger(RestrictMailServiceImpl.class);

	@Autowired
	private RestrictMailDao restrictMailDao;

	@Override
	public List<String> getRestrictEmployees() throws Exception {
		List<String> restrictEmpList = restrictMailDao.getRestrictEmployees();
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
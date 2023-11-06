package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.SequenceMstDao;
import com.ksvsofttech.product.entities.SequenceMst;
import com.ksvsofttech.product.service.SequenceMstService;

@Service
public class SequenceMstServiceImpl implements SequenceMstService {
	private static final Logger LOGGER = LogManager.getLogger(SequenceMstServiceImpl.class);
	
	@Autowired
	private SequenceMstDao sequenceMstDao;
	
	@Override
	public List<SequenceMst> getTenantId() throws Exception {
		List<SequenceMst> sequenceMst = sequenceMstDao.getTenantId();
		try {
			if(Objects.nonNull(sequenceMst)) {
				return sequenceMst;
			} else {
				System.out.println("sequenceMst is null");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get tenant id " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}
}

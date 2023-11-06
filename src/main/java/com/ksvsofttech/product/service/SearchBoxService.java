package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.EmpBasicDetails;

public interface SearchBoxService {

	public List<EmpBasicDetails> getSearchEmployeeByFullName(String value) throws Exception;
}

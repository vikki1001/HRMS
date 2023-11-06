package com.ksvsofttech.product.dao;

import java.util.List;

import com.ksvsofttech.product.entities.EmpBasicDetails;

public interface SearchBoxDao {

	public List<EmpBasicDetails> getSearchEmployeeByFullName(String value) throws Exception;
}

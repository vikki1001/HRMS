package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.PayrollDao;
import com.ksvsofttech.product.entities.PayrollMst;
import com.ksvsofttech.product.repository.PayrollRepository;

@Repository
@Transactional
public class PayrollDaoImpl implements PayrollDao {
	private static final Logger LOGGER = LogManager.getLogger(PayrollDaoImpl.class);

	@Autowired
	private PayrollRepository payrollRepository;

	/* For Save/Upload Payroll Excel File */
	@Override
	public void saveAll(List<PayrollMst> payrollMst) throws Exception {
		try {
			 this.payrollRepository.saveAll(payrollMst);
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Save Data ::::::  " + ExceptionUtils.getStackTrace(e));
		}
	}
	
	/* Current Month Payroll Dispaly for Employee*/
	@Override
	public List<PayrollMst> getPayrollSlipByMonthAndYear(String month, String year, String empId) throws Exception {
		List<PayrollMst> payrollMstList = payrollRepository.getPayrollSlipByMonthAndYear(month, year, empId);
		try {
			if (Objects.nonNull(payrollMstList)) {
				return payrollMstList;
			} else {
				System.out.println("List is empty ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error Occuring While Display Payroll List ::::::  " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}
	
	/* Get all Records into DB */
	@Override
	public List<PayrollMst> findAll() throws Exception {
		List<PayrollMst> payrollMsts = payrollRepository.findAll();
		try {
			if (Objects.nonNull(payrollMsts)) {
				return payrollMsts;
			} else {

			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display all holiday ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No holiday record exist......");
		}
		return new ArrayList<>();
	}
}
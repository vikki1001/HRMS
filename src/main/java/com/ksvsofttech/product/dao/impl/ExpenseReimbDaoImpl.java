package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.ExpenseReimbDao;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.repository.EmpBasicRepository;
import com.ksvsofttech.product.repository.ExpenseReimbRepository;

@Repository
public class ExpenseReimbDaoImpl implements ExpenseReimbDao {
	private static final Logger LOGGER = LogManager.getLogger(ExpenseReimbDaoImpl.class);

	@Autowired
	private ExpenseReimbRepository expenseReimbRepository;
	@Autowired
	private EmpBasicRepository empBasicRepository;

	/* List of Active Expense Reimbursement */
	@Override
	public List<ExpenseReimb> activeExpenseReimb(String empId) throws Exception {
		List<ExpenseReimb> expenseList = expenseReimbRepository.activeExpenseReimb(empId);
		try {
			if (!expenseList.isEmpty()) {
				return expenseList;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display isactive expense list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No Expense record exist ......");
		}
		return new ArrayList<>();
	}

	/* List of Cancel Expense Reimbursement */
	@Override
	public List<ExpenseReimb> cancelExpenseReimb(String empId) throws Exception {
		List<ExpenseReimb> expenseList = expenseReimbRepository.cancelExpenseReimb(empId);
		try {
			if (!expenseList.isEmpty()) {
				return expenseList;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display inactive expense list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No expense record exist ......");
		}
		return new ArrayList<>();
	}

	/* List of Cancel Expense Reimbursement */
	@Override
	public ExpenseReimb getCancelById(ExpenseReimb expenseReimb) throws Exception {
		try {
			Optional<ExpenseReimb> optional = expenseReimbRepository.getCancelById(expenseReimb.getId());
			if (optional.isPresent()) {
				ExpenseReimb newExpenseReimb = optional.get();
				newExpenseReimb.setIsActive(expenseReimb.getIsActive());
				newExpenseReimb.setIsActive("0");
				expenseReimbRepository.save(newExpenseReimb);
				return newExpenseReimb;
			} else {
				return expenseReimb;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while deactive Employee------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No employee deactivate......" + expenseReimb.getEmployeeName());
		}
	}

	/* Save Expense Reimbursement Data */
	@Override
	public ExpenseReimb saveExpenseReimb(ExpenseReimb expenseReimb) throws Exception {
		String empId;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		empId = authentication.getName();
		EmpBasicDetails empBasicDetails = null;
		try {
			if (Objects.nonNull(empId)) {
				empBasicDetails = empBasicRepository.getCurrentUser(empId);
				if (Objects.isNull(expenseReimb.getId()) && Objects.nonNull(empBasicDetails)) {
					expenseReimb.setIsActive("1");
					expenseReimb.setEmpId(empId);
					expenseReimb.setManagerId(empBasicDetails.getEmpWorkDetails().getReportingManager());
					expenseReimb.setStatus("Pending");
					expenseReimb.setNotification("Unread");
					expenseReimb.setCreatedDate(new Date());
					expenseReimb.setCreatedBy(empId);
					expenseReimbRepository.save(expenseReimb);
				} else {
					return expenseReimb;
				}
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update employee Exit Activity------"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No user save & update...... " + expenseReimb.getEmployeeName());
		}
		return expenseReimb;
	}

	/* Expense Reimbursement Apply Mail Send Project Manager, HR & Employee */
	@Override
	public List<ExpenseReimb> getExpenseReimbByEmpId(String empId) throws Exception {
		try {
			return expenseReimbRepository.getExpenseReimbByEmpId(empId);
		} catch (Exception e) {
			LOGGER.error("------Error occur while get expense reimbursement by empId -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + empId);
		}
	}

	/* Display List of Expense Reimbursement to Manager */
	@Override
	public List<ExpenseReimb> getEmpWithManger() throws Exception {
		try {
			return expenseReimbRepository.getEmpWithManger();
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display expense reimbursement to manager -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... ");
		}
	}

	/* Accept/Reject Expense Reimbursement by Manager */
	@Override
	public List<ExpenseReimb> acceptExpenseReimbById(Long id) throws Exception {
		try {
			return expenseReimbRepository.acceptExpenseReimbById(id);
		} catch (Exception e) {
			LOGGER.error(
					"------ Error occur while get expense reimbursement by Id -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}

	/* Add Some Data in Registered Expense Reimbursement by Approved/Reject Request by Manager */
	@Override
	public void acceptStatus(String status, String flag, Long id) throws Exception {
		try {
			expenseReimbRepository.acceptStatus(status, flag, id);
		} catch (Exception e) {
			LOGGER.error(
					"------ Error occur while update expense reimbursement to manager approved/Reject request -----"
							+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}

	/* Display Attachment(Image) */
	@Override
	public ExpenseReimb getAttachment(Long id) throws Exception {
		try {
			return expenseReimbRepository.getAttachment(id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display attachment (Image) -----" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}
	/* Display List of Expense Reimbursement to Manager with Pending Status */
	@Override
	public List<ExpenseReimb> getEmpWithMangerWithPending(String empId) throws Exception {
		try {
			return expenseReimbRepository.getEmpWithMangerWithPending(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display expense reimbursement to manager with pending status -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " );
		}
	}
	
	@Override
	public ExpenseReimb notificationRead(ExpenseReimb expenseReimb) throws Exception {
		try {
			
			Optional<ExpenseReimb> optional = expenseReimbRepository.findById(expenseReimb.getId());
			if (optional.isPresent()) {
				ExpenseReimb reimb = optional.get();
				reimb.setNotification("Read");
				
				expenseReimbRepository.save(reimb);
				return reimb;
			} else {

			}
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return expenseReimb;
	}
}
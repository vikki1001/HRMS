package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.ExpenseReimbDao;
import com.ksvsofttech.product.entities.ExpenseReimb;
import com.ksvsofttech.product.service.ExpenseReimbService;

@Service
public class ExpenseReimbServiceImpl implements ExpenseReimbService {
	private static final Logger LOGGER = LogManager.getLogger(ExpenseReimbServiceImpl.class);

	@Autowired
	private ExpenseReimbDao expenseReimbDao;

	/* List of Active Expense Reimbursement */
	@Override
	public List<ExpenseReimb> activeExpenseReimb(String empId) throws Exception {
		List<ExpenseReimb> expenseList = new ArrayList<>();
		try {
			expenseList = expenseReimbDao.activeExpenseReimb(empId);
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display isactive Expense list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No isactive Expense record exist......");
		}
		return expenseList;
	}

	@Override
	public List<ExpenseReimb> cancelExpenseReimb(String empId) throws Exception {
		List<ExpenseReimb> expenseList = new ArrayList<>();
		try {
			expenseList = expenseReimbDao.cancelExpenseReimb(empId);
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display inactive Expense list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No inactive Expense record exist......");
		}
		return expenseList;
	}

	/* List of Cancel Expense Reimbursement */
	@Override
	public ExpenseReimb getCancelById(ExpenseReimb expenseReimb) throws Exception {
		try {
			expenseReimb = expenseReimbDao.getCancelById(expenseReimb);
		} catch (Exception e) {
			LOGGER.error("------Error occur  while activate------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No one is deactive......" + expenseReimb.getEmployeeName());
		}
		return expenseReimb;
	}

	/* Save Expense Reimbursement Data */
	@Override
	public ExpenseReimb saveExpenseReimb(ExpenseReimb expenseReimb) throws Exception {
		try {
			expenseReimbDao.saveExpenseReimb(expenseReimb);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save & update Expense Reimbursement-----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No user save & update......" + expenseReimb.getEmployeeName());
		}
		return expenseReimb;
	}

	/* Expense Reimbursement Apply Mail Send Project Manager, HR & Employee */
	@Override
	public List<ExpenseReimb> getExpenseReimbByEmpId(String empId) throws Exception {
		try {
			return expenseReimbDao.getExpenseReimbByEmpId(empId);
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
			return expenseReimbDao.getEmpWithManger();
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display expense reimbursement to manager -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " );
		}
	}

	/* Accept/Reject Expense Reimbursement by Manager */
	@Override
	public List<ExpenseReimb> acceptExpenseReimbById(Long id) throws Exception {
		try {
			return expenseReimbDao.acceptExpenseReimbById(id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while get expense reimbursement by Id -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}		
	}

	/* Add Some Data in Registered Expense Reimbursement by Approved/Reject Request by Manager */
	@Override
	public void acceptStatus(String status, String flag, Long id) throws Exception {
		try {
			 expenseReimbDao.acceptStatus(status, flag, id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while update expense reimbursement to manager approved/Reject request -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}		
	}

	/* Display Attachment(Image) */
	@Override
	public ExpenseReimb getAttachment(Long id) throws Exception {
		try {
			return expenseReimbDao.getAttachment(id);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display attachment (Image) -----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " + id);
		}
	}

	/* Display List of Expense Reimbursement to Manager with Pending Status */
	@Override
	public List<ExpenseReimb> getEmpWithMangerWithPending(String empId) throws Exception {
		try {
			return expenseReimbDao.getEmpWithMangerWithPending(empId);
		} catch (Exception e) {
			LOGGER.error("------ Error occur while display expense reimbursement to manager with pending status-----"
					+ ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist ...... " );
		}
	}
	
	@Override
	public ExpenseReimb notificationRead(ExpenseReimb expenseReimb) throws Exception {
		try {
			expenseReimbDao.notificationRead(expenseReimb);
		} catch (Exception e) {
			LOGGER.error("Error occur while save notification /n" + ExceptionUtils.getStackTrace(e));
		}
		return expenseReimb;
	}
}
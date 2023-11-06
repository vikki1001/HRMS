package com.ksvsofttech.product.service;

import java.util.List;

import com.ksvsofttech.product.entities.ExpenseReimb;

public interface ExpenseReimbService {

	public ExpenseReimb getCancelById(ExpenseReimb expenseReimb) throws Exception;

	public ExpenseReimb saveExpenseReimb(ExpenseReimb expenseReimb) throws Exception;

	public List<ExpenseReimb> activeExpenseReimb(String empId) throws Exception;

	public List<ExpenseReimb> cancelExpenseReimb(String empId) throws Exception;

	public List<ExpenseReimb> getExpenseReimbByEmpId(String empId) throws Exception;

	public List<ExpenseReimb> getEmpWithManger() throws Exception;

	public List<ExpenseReimb> acceptExpenseReimbById(Long id) throws Exception;

	public void acceptStatus(String status, String flag, Long id) throws Exception;

	public ExpenseReimb getAttachment(Long id) throws Exception;

	public List<ExpenseReimb> getEmpWithMangerWithPending(String empId) throws Exception;

	public ExpenseReimb notificationRead(ExpenseReimb expenseReimb) throws Exception;
}
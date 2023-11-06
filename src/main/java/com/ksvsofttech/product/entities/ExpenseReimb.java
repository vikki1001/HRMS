package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EXPENSEREIMB")
public class ExpenseReimb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10)
	private Long id;

	@Transient
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEEID")
	private EmpBasicDetails empBasicDetails;

	@Column(name = "EMPID", length = 30)
	private String empId;

	@Column(name = "EMPLOYEENAME", length = 30)
	private String employeeName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE", length = 20)
	private String date;

	@Column(name = "REIMBURSEMENTTYPE", length = 50)
	private String reimbursementType;

	@Column(name = "CURRENCY", length = 10)
	private String currency;

	@Lob
	@Column(name = "ATTACHMENT")
	private byte[] attachment;

	@Column(name = "EXPENSEAMOUNT", length = 20)
	private String expenseAmount;

	@Column(name = "REASONSFOREXPENSE", length = 120)
	private String reasonsForExpense;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "CREATEDBY", length = 30)
	private String createdBy;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "LASTMODBY", length = 30)
	private String lastModifiedBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "MANAGERID", length = 50)
	private String managerId;

	@Column(name = "FLAG", length = 10)
	private String flag;

	@Column(name = "STATUS", length = 20)
	private String status;

	@Column(name = "NOTIFICATION", length = 20)
	private String notification;

	public ExpenseReimb() {
		super();
	}

	public ExpenseReimb(byte[] attachment) {
		super();
		this.attachment = attachment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getReasonsForExpense() {
		return reasonsForExpense;
	}

	public void setReasonsForExpense(String reasonsForExpense) {
		this.reasonsForExpense = reasonsForExpense;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
}
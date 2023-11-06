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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXITACTIVITY")
public class ExitActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPID")
	private EmpBasicDetails empId;

	@Column(name = "EMPLOYEETYPE", length = 50)
	private String employeeType;

	@Column(name = "REQLASTWORKINGDTE")
	private String reqLastWorkingDte;

	@Column(name = "REGDATE")
	private String regDate;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "MANAGERID", length = 50)
	private String managerId;

	@Column(name = "FLAG", length = 10)
	private String flag;

	@Column(name = "STATUS", length = 20)
	private String status;

	@Column(name = "NOTIFICATION", length = 20)
	private String notification;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmpBasicDetails getEmpId() {
		return empId;
	}

	public void setEmpId(EmpBasicDetails empId) {
		this.empId = empId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getReqLastWorkingDte() {
		return reqLastWorkingDte;
	}

	public void setReqLastWorkingDte(String reqLastWorkingDte) {
		this.reqLastWorkingDte = reqLastWorkingDte;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
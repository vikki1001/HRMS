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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LEAVEMST")
public class LeaveMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 10)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPID")
	private EmpBasicDetails empId;

	@Column(name = "FULLNAME", length = 50)
	private String fullName;

	@Column(name = "LEAVETYPE", length = 20)
	private String leaveType;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "FROMDATE", length = 20, columnDefinition = "Timestamp")
	private String fromDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "TODATE", length = 20, columnDefinition = "Timestamp")
	private String toDate;

	@Column(name = "DAYLEAVE", length = 10)
	private Float dayLeave;

	@Column(name = "LEAVEDETAILS", length = 100)
	private String leaveDetails;

	@Column(name = "PROJECTCODE", length = 20)
	private String projectCode;

	@Column(name = "ADDRDURINGLEAVE", length = 50)
	private String addrDuringLeave;

	@Column(name = "PHONENO", length = 10)
	private String phoneNo;

	@Column(name = "COMMENTS", length = 100)
	private String comments;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE", length = 20)
	private String date;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "LASTMODIFIEDDATE", length = 20)
	private Date lastModifiedDate;

	@Column(name = "LASTMODIFIEDBY", length = 20)
	private String lastModifiedBy;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "MANAGERID", length = 50)
	private String managerId;

	@Column(name = "STATUS", length = 20)
	private String status;

	@Column(name = "FLAG", length = 10)
	private String flag;

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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Float getDayLeave() {
		return dayLeave;
	}

	public void setDayLeave(Float dayLeave) {
		this.dayLeave = dayLeave;
	}

	public String getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(String leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getAddrDuringLeave() {
		return addrDuringLeave;
	}

	public void setAddrDuringLeave(String addrDuringLeave) {
		this.addrDuringLeave = addrDuringLeave;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

}
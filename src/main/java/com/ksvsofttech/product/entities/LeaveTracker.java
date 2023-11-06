package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LEAVETRACKER")
public class LeaveTracker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "EMPID", length = 50)
	private String empId;

	@Column(name = "TOTALLEAVE", length = 50)
	private Float totalLeave;

	@Column(name = "PAIDLEAVE", length = 50)
	private Float paidLeave;

	@Column(name = "MATERNITYLEAVE", length = 50)
	private Float maternityLeave;

	@Column(name = "BOOKEDTOTALLEAVE", length = 50)
	private Float bookedTotalLeave;

	@Column(name = "BOOKEDPAIDLEAVE", length = 50)
	private Float bookedPaidLeave;

	@Column(name = "BOOKEDMATERNITYLEAVE", length = 50)
	private Float bookedMaternityLeave;

	@Column(name = "ADDLEAVE", length = 50)
	private Float addLeave;

	@Column(name = "LEAVETYPE", length = 20)
	private String leaveType;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY")
	private String createdBy;

	@Column(name = "LASTMODIFIEDDATE", length = 20)
	private Date lastModifiedDate;

	@Column(name = "LASTMODIFIEDBY", length = 20)
	private String lastModifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Float getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(Float totalLeave) {
		this.totalLeave = totalLeave;
	}

	public Float getPaidLeave() {
		return paidLeave;
	}

	public void setPaidLeave(Float paidLeave) {
		this.paidLeave = paidLeave;
	}

	public Float getBookedMaternityLeave() {
		return bookedMaternityLeave;
	}

	public void setBookedMaternityLeave(Float bookedMaternityLeave) {
		this.bookedMaternityLeave = bookedMaternityLeave;
	}

	public Float getBookedTotalLeave() {
		return bookedTotalLeave;
	}

	public void setBookedTotalLeave(Float bookedTotalLeave) {
		this.bookedTotalLeave = bookedTotalLeave;
	}

	public Float getBookedPaidLeave() {
		return bookedPaidLeave;
	}

	public void setBookedPaidLeave(Float bookedPaidLeave) {
		this.bookedPaidLeave = bookedPaidLeave;
	}

	public Float getMaternityLeave() {
		return maternityLeave;
	}

	public void setMaternityLeave(Float maternityLeave) {
		this.maternityLeave = maternityLeave;
	}

	public Float getAddLeave() {
		return addLeave;
	}

	public void setAddLeave(Float addLeave) {
		this.addLeave = addLeave;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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

	@Override
	public String toString() {
		return "LeaveTracker [id=" + id + ", empId=" + empId + ", totalLeave=" + totalLeave + ", paidLeave=" + paidLeave
				+ ", maternityLeave=" + maternityLeave + ", bookedTotalLeave=" + bookedTotalLeave + ", bookedPaidLeave="
				+ bookedPaidLeave + ", bookedMaternityLeave=" + bookedMaternityLeave + ", addLeave=" + addLeave
				+ ", leaveType=" + leaveType + ", isActive=" + isActive + ", tenantId=" + tenantId + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate
				+ ", lastModifiedBy=" + lastModifiedBy + "]";
	}
	
	
}
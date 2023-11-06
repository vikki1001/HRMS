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
@Table(name = "CHECKINCHECKOUT")
public class CheckInCheckOut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERID")
	private String userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERID", insertable = false, updatable = false)
	private EmpBasicDetails empBasicDetails;

	@Column(name = "TENANTID")
	private String tenantId;

	@Column(name = "CHECKINDTTM")
	private String checkinDateTime;

	@Column(name = "CHECKOUTDTTM")
	private String checkoutDateTime;

	@Column(name = "DAYOFTHEWEEK")
	private String dayOfTheWeek;

	@Column(name = "CHECKINACTIONFROM")
	private String checkinActionFrom;

	@Column(name = "CHECKOUTACTIONFROM")
	private String checkoutActionFrom;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "CHECKINIPADDRESS")
	private String checkinIpAddress;

	@Column(name = "CHECKOUTIPADDRESS")
	private String checkoutIpAddress;

	@Column(name = "TIMEDURATION")
	private String timeDuration;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "DATE")
	private String date;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "FLAG", length = 10)
	private String flag;

	@Column(name = "STATUS", length = 10)
	private String status;

	@Column(name = "REASON", length = 100)
	private String reason;

	@Column(name = "DESCRIPTION", length = 120)
	private String description;

	@Column(name = "MANAGERID", length = 20)
	private String managerId;

	@Column(name = "APPROVALREQUEST", length = 20)
	private String approvalReq;

	@Column(name = "ISACTIVE", length = 20)
	private String isActive;

	@Column(name = "ADDNEWREQ", length = 10)
	private String addNewReq;

	@Column(name = "REMARK", length = 20)
	private String remark;

	@Column(name = "NOTIFICATION", length = 20)
	private String notification;

	public CheckInCheckOut() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCheckinDateTime() {
		return checkinDateTime;
	}

	public void setCheckinDateTime(String checkinDateTime) {
		this.checkinDateTime = checkinDateTime;
	}

	public String getCheckoutDateTime() {
		return checkoutDateTime;
	}

	public void setCheckoutDateTime(String checkoutDateTime) {
		this.checkoutDateTime = checkoutDateTime;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getCheckinActionFrom() {
		return checkinActionFrom;
	}

	public void setCheckinActionFrom(String checkinActionFrom) {
		this.checkinActionFrom = checkinActionFrom;
	}

	public String getCheckoutActionFrom() {
		return checkoutActionFrom;
	}

	public void setCheckoutActionFrom(String checkoutActionFrom) {
		this.checkoutActionFrom = checkoutActionFrom;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCheckinIpAddress() {
		return checkinIpAddress;
	}

	public void setCheckinIpAddress(String checkinIpAddress) {
		this.checkinIpAddress = checkinIpAddress;
	}

	public String getCheckoutIpAddress() {
		return checkoutIpAddress;
	}

	public void setCheckoutIpAddress(String checkoutIpAddress) {
		this.checkoutIpAddress = checkoutIpAddress;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getApprovalReq() {
		return approvalReq;
	}

	public void setApprovalReq(String approvalReq) {
		this.approvalReq = approvalReq;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAddNewReq() {
		return addNewReq;
	}

	public void setAddNewReq(String addNewReq) {
		this.addNewReq = addNewReq;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "CheckInCheckOut [date=" + date + "]";
	}

}
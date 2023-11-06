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
@Table(name = "DEPARTMENTMST")
public class DepartmentMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "DEPARTMENTID", length = 10)
	private Long departmentId;
	@Column(name = "ROLEID", length = 10)
	private Long roleId;
	@Column(name = "DEPARTMENTNAME", length = 50)
	private String departmentName;
	@Column(name = "DEPARTMENTDESC", length = 100)
	private String departmentDesc;
	@Column(name = "GRADE", length = 10)
	private String grade;
	@Column(name = "IPADD", length = 50)
	private String ipAddress;
	@Column(name = "TENANTID", length = 20)
	private String tenantId;
	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;
	@Column(name = "ISACTIVE", length = 10)
	private String isActive;
	@Column(name = "CREATEDDATE")
	private Date createdDate;
	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;
	@Column(name = "HOSTNAME", length = 50)
	private String hostName;
	@Column(name = "AUTHSTATUS", length = 50)
	private String authStatus;
	@Column(name = "VERSION", length = 20)
	private Integer version;
	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;
	@Column(name = "ISADMFLG", length = 20)
	private Integer isAdminFlag;
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getIsAdminFlag() {
		return isAdminFlag;
	}
	public void setIsAdminFlag(Integer isAdminFlag) {
		this.isAdminFlag = isAdminFlag;
	}
}

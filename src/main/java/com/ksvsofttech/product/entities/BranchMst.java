package com.ksvsofttech.product.entities;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRANCHMST")
public class BranchMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BRANCHID", length = 10)
	private Long branchId;

	@Column(name = "BRANCHCODE", length = 50)
	private String branchCode;

	@Column(name = "BRANCHNAME", length = 50)
	private String branchName;

	@Column(name = "CITY", length = 50)
	private String city;

	@Column(name = "TELNO", length = 15)
	private String telephoneno;

	@Column(name = "BRANCHTYPECODE", length = 50)
	private String branchTypeCode;

	@Column(name = "COUNTRY", length = 50)
	private String country;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "VERSION", length = 20)
	private Integer version;

	@Column(name = "AUTHSTATUS", length = 20)
	private String authStatus;

	@Column(name = "BRANCHREFCODE", length = 20)
	private String branchRefCode;

	@Column(name = "ADDRESS1", length = 30)
	private String address1;

	@Column(name = "ADDRESS2", length = 30)
	private String address2;

	@Column(name = "ADDRESS3", length = 30)
	private String address3;

	@Column(name = "BRANCHTYPE", length = 20)
	private String branchType;

	@Column(name = "POSTALCODE", length = 10)
	private String postalCode;

	@Column(name = "STATE", length = 30)
	private String state;

	@Column(name = "CONTACTPERSON", length = 50)
	private String contactPerson;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "MOBILE", length = 15)
	private String mobile;

	@Column(name = "FAX", length = 15)
	private String fax;

	@Column(name = "BRANCHCODENAME", length = 50)
	private String branchCodeName;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephoneno() {
		return telephoneno;
	}

	public void setTelephoneno(String telephoneno) {
		this.telephoneno = telephoneno;
	}

	public String getBranchTypeCode() {
		return branchTypeCode;
	}

	public void setBranchTypeCode(String branchTypeCode) {
		this.branchTypeCode = branchTypeCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIsactive() {
		return isActive;
	}

	public void setIsactive(String isActive) {
		this.isActive = isActive;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getBranchRefCode() {
		return branchRefCode;
	}

	public void setBranchRefCode(String branchRefCode) {
		this.branchRefCode = branchRefCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getBranchCodeName() {
		return branchCodeName;
	}

	public void setBranchCodeName(String branchCodeName) {
		this.branchCodeName = branchCodeName;
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

	public static class Comparators {
		public static Comparator<BranchMst> BRANCH = new Comparator<BranchMst>() {

			@Override
			public int compare(BranchMst o1, BranchMst o2) {
				return o1.branchName.compareTo(o2.branchName);
			}
		};
	}
}
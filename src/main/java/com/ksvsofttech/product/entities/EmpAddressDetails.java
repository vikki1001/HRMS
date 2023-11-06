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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "EMPADDRESSDETAILS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpAddressDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PRESENTADDRESS", length = 50)
	private String presentAddress;

	@Column(name = "PERMANENTADDRESS", length = 50)
	private String permanentAddress;

	@Column(name = "PERSENTCITY", length = 50)
	private String presentCity;

	@Column(name = "PERMANENTCITY", length = 50)
	private String permanentCity;

	@Column(name = "PERSENTSTATE", length = 50)
	private String presentState;

	@Column(name = "PERMANENTSTATE", length = 20)
	private String permanentState;

	@Column(name = "PERSENTPINCODE", length = 20)
	private String presentPinCode;

	@Column(name = "PERMANENTPINCODE", length = 20)
	private String permanentPinCode;

	@Column(name = "PERSENTCOUNTRY", length = 20)
	private String presentCountry;

	@Column(name = "PERMANENTCOUNTRY", length = 50)
	private String permanentCountry;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODBY", length = 50)
	private String lastModifiedBy;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEEID", unique = true)
	private EmpBasicDetails empBasicDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPresentState() {
		return presentState;
	}

	public void setPresentState(String presentState) {
		this.presentState = presentState;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getPresentPinCode() {
		return presentPinCode;
	}

	public void setPresentPinCode(String presentPinCode) {
		this.presentPinCode = presentPinCode;
	}

	public String getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(String permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}

	public String getPresentCountry() {
		return presentCountry;
	}

	public void setPresentCountry(String presentCountry) {
		this.presentCountry = presentCountry;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
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

}

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPPASSPORTDETAILS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpPassportDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "PASSPORTNO", length = 50)
	private String passportNo;

	@Column(name = "PASSPORTNAME", length = 50)
	private String passportName;

	@Column(name = "PASSPORTADDRESS", length = 50)
	private String passportAddress;

	@Column(name = "PASSPORTCOUNTRY", length = 50)
	private String passportCountry;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "PASSPORTDATEOFISSUE")
	private String passportDateOfIssue;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "PASSPORTDATEOFEXPIRE")
	private String passportDateOfExpire;

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

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	public String getPassportAddress() {
		return passportAddress;
	}

	public void setPassportAddress(String passportAddress) {
		this.passportAddress = passportAddress;
	}

	public String getPassportCountry() {
		return passportCountry;
	}

	public void setPassportCountry(String passportCountry) {
		this.passportCountry = passportCountry;
	}

	public String getPassportDateOfIssue() {
		return passportDateOfIssue;
	}

	public void setPassportDateOfIssue(String passportDateOfIssue) {
		this.passportDateOfIssue = passportDateOfIssue;
	}

	public String getPassportDateOfExpire() {
		return passportDateOfExpire;
	}

	public void setPassportDateOfExpire(String passportDateOfExpire) {
		this.passportDateOfExpire = passportDateOfExpire;
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

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

}

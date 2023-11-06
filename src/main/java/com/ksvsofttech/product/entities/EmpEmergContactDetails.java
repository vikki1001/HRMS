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

@Entity
@Table(name = "EMPEMERGCONTACTDETAILS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpEmergContactDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "EMERGCONTACTNAME1", length = 50)
	private String emergContactName1;

	@Column(name = "EMERGCONTACTNO1", length = 10)
	private String emergContactNo1;

	@Column(name = "EMERGCONTACTNAME2", length = 50)
	private String emergContactName2;

	@Column(name = "EMERGCONTACTNO2", length = 10)
	private String emergContactNo2;

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

	public String getEmergContactName1() {
		return emergContactName1;
	}

	public void setEmergContactName1(String emergContactName1) {
		this.emergContactName1 = emergContactName1;
	}

	public String getEmergContactNo1() {
		return emergContactNo1;
	}

	public void setEmergContactNo1(String emergContactNo1) {
		this.emergContactNo1 = emergContactNo1;
	}

	public String getEmergContactName2() {
		return emergContactName2;
	}

	public void setEmergContactName2(String emergContactName2) {
		this.emergContactName2 = emergContactName2;
	}

	public String getEmergContactNo2() {
		return emergContactNo2;
	}

	public void setEmergContactNo2(String emergContactNo2) {
		this.emergContactNo2 = emergContactNo2;
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

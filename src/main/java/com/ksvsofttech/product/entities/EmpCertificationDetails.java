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
@Table(name = "EMPCERTIDETAILS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmpCertificationDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "CERTIFICATIONNAME", length = 50)
	private String certificationName;

	@Column(name = "CERTVERSION", length = 10)
	private String certVersion;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CERTCOMPLETEDATE")
	private String certCompleteDate;

	@Column(name = "CERTVALIDTILL", length = 50)
	private String certValidTill;

	@Column(name = "CERTSCORE", length = 50)
	private String certScore;

	@Column(name = "CERTCODE", length = 50)
	private String certCode;

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

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	
	public String getCertVersion() {
		return certVersion;
	}

	public void setCertVersion(String certVersion) {
		this.certVersion = certVersion;
	}

	public String getCertCompleteDate() {
		return certCompleteDate;
	}

	public void setCertCompleteDate(String certCompleteDate) {
		this.certCompleteDate = certCompleteDate;
	}

	public String getCertValidTill() {
		return certValidTill;
	}

	public void setCertValidTill(String certValidTill) {
		this.certValidTill = certValidTill;
	}

	public String getCertScore() {
		return certScore;
	}

	public void setCertScore(String certScore) {
		this.certScore = certScore;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
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

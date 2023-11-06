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
@Table(name = "PAYROLLMST")
public class PayrollMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPID")
	private EmpBasicDetails empBasicDetails;

//	@Column(name = "EMPID")
//	private String empId;

	@Column(name = "NAMEOFEMP")
	private String nameOfEmp;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "WORKINGDAYS")
	private Double workingDays;

	@Column(name = "BASICSALARY")
	private Double basicSalary;

	@Column(name = "DA")
	private Double dearnessAllowances;

	@Column(name = "CONVEYANCE")
	private Double conveyance;

	@Column(name = "HOUSERENT")
	private Double houseRent;

	@Column(name = "MEDICALBENEFIT")
	private Double medicalBenefit;

	@Column(name = "OVERTIMEHRS")
	private Double overTimeHours;

	@Column(name = "OVERTIMERATE")
	private Double overTimeRate;

	@Column(name = "VARIABLEPAY")
	private Double variablePay;

	@Column(name = "GROSSSALARY")
	private Double grossSalary;

	@Column(name = "INCOMETAX")
	private Double incomeTax;

	@Column(name = "EPF")
	private Double empProvidentFundOrg;

	@Column(name = "NOOFLEAVES")
	private Double noOfLeaves;

	@Column(name = "LEAVERATE")
	private Double leavesRate;

	@Column(name = "TOTALDEDUCTION")
	private Double totalDeduction;

	@Column(name = "NETSALARY")
	private Double netSalary;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE")
	private String date;

	@Column(name = "ISACTIVE", length = 10)
	private String isActive;

	@Column(name = "TENANTID", length = 10)
	private String tenantId;

	@Column(name = "CREATEDDATE")
	private Date createdDate;

	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;

	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	@Column(name = "LASTMODIFIEDBY", length = 50)
	private String lastModifiedBy;

	@Column(name = "FLAG")
	private String flag;

	public PayrollMst() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmpBasicDetails getEmpBasicDetails() {
		return empBasicDetails;
	}

	public void setEmpBasicDetails(EmpBasicDetails empBasicDetails) {
		this.empBasicDetails = empBasicDetails;
	}

	public String getNameOfEmp() {
		return nameOfEmp;
	}

	public void setNameOfEmp(String nameOfEmp) {
		this.nameOfEmp = nameOfEmp;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Double workingDays) {
		this.workingDays = workingDays;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getDearnessAllowances() {
		return dearnessAllowances;
	}

	public void setDearnessAllowances(Double dearnessAllowances) {
		this.dearnessAllowances = dearnessAllowances;
	}

	public Double getConveyance() {
		return conveyance;
	}

	public void setConveyance(Double conveyance) {
		this.conveyance = conveyance;
	}

	public Double getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(Double houseRent) {
		this.houseRent = houseRent;
	}

	public Double getMedicalBenefit() {
		return medicalBenefit;
	}

	public void setMedicalBenefit(Double medicalBenefit) {
		this.medicalBenefit = medicalBenefit;
	}

	public Double getOverTimeHours() {
		return overTimeHours;
	}

	public void setOverTimeHours(Double overTimeHours) {
		this.overTimeHours = overTimeHours;
	}

	public Double getOverTimeRate() {
		return overTimeRate;
	}

	public void setOverTimeRate(Double overTimeRate) {
		this.overTimeRate = overTimeRate;
	}

	public Double getVariablePay() {
		return variablePay;
	}

	public void setVariablePay(Double variablePay) {
		this.variablePay = variablePay;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Double incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Double getEmpProvidentFundOrg() {
		return empProvidentFundOrg;
	}

	public void setEmpProvidentFundOrg(Double empProvidentFundOrg) {
		this.empProvidentFundOrg = empProvidentFundOrg;
	}

	public Double getNoOfLeaves() {
		return noOfLeaves;
	}

	public void setNoOfLeaves(Double noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}

	public Double getLeavesRate() {
		return leavesRate;
	}

	public void setLeavesRate(Double leavesRate) {
		this.leavesRate = leavesRate;
	}

	public Double getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public PayrollMst(Long id, EmpBasicDetails empBasicDetails, String empId, String nameOfEmp, String designation,
			Double workingDays, Double basicSalary, Double dearnessAllowances, Double conveyance, Double houseRent,
			Double medicalBenefit, Double overTimeHours, Double overTimeRate, Double variablePay, Double grossSalary,
			Double incomeTax, Double empProvidentFundOrg, Double noOfLeaves, Double leavesRate, Double totalDeduction,
			Double netSalary, String date, String isActive, String tenantId, Date createdDate, String createdBy,
			Date lastModifiedDate, String lastModifiedBy) {
		super();
		this.id = id;
		this.empBasicDetails = empBasicDetails;
		this.nameOfEmp = nameOfEmp;
		this.designation = designation;
		this.workingDays = workingDays;
		this.basicSalary = basicSalary;
		this.dearnessAllowances = dearnessAllowances;
		this.conveyance = conveyance;
		this.houseRent = houseRent;
		this.medicalBenefit = medicalBenefit;
		this.overTimeHours = overTimeHours;
		this.overTimeRate = overTimeRate;
		this.variablePay = variablePay;
		this.grossSalary = grossSalary;
		this.incomeTax = incomeTax;
		this.empProvidentFundOrg = empProvidentFundOrg;
		this.noOfLeaves = noOfLeaves;
		this.leavesRate = leavesRate;
		this.totalDeduction = totalDeduction;
		this.netSalary = netSalary;
		this.date = date;
		this.isActive = isActive;
		this.tenantId = tenantId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public String toString() {
		return "PayrollMst [id=" + id + ", empBasicDetails=" + empBasicDetails + ", nameOfEmp=" + nameOfEmp
				+ ", designation=" + designation + ", workingDays=" + workingDays + ", basicSalary=" + basicSalary
				+ ", dearnessAllowances=" + dearnessAllowances + ", conveyance=" + conveyance + ", houseRent="
				+ houseRent + ", medicalBenefit=" + medicalBenefit + ", overTimeHours=" + overTimeHours
				+ ", overTimeRate=" + overTimeRate + ", variablePay=" + variablePay + ", grossSalary=" + grossSalary
				+ ", incomeTax=" + incomeTax + ", empProvidentFundOrg=" + empProvidentFundOrg + ", noOfLeaves="
				+ noOfLeaves + ", leavesRate=" + leavesRate + ", totalDeduction=" + totalDeduction + ", netSalary="
				+ netSalary + ", date=" + date + ", isActive=" + isActive + ", tenantId=" + tenantId + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", lastModifiedDate=" + lastModifiedDate
				+ ", lastModifiedBy=" + lastModifiedBy + "]";
	}

}
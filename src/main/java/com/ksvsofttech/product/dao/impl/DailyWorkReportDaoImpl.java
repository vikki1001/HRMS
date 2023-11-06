package com.ksvsofttech.product.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.ksvsofttech.product.dao.DailyWorkReportDao;
import com.ksvsofttech.product.entities.DailyWorkReport;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.repository.DailyWorkReportRepository;
import com.ksvsofttech.product.repository.EmpBasicRepository;

@Repository
public class DailyWorkReportDaoImpl implements DailyWorkReportDao {
	private static final Logger LOGGER = LogManager.getLogger(DailyWorkReportDaoImpl.class);

	@Autowired
	private EmpBasicRepository empBasicRepository;
	
	@Autowired
	private DailyWorkReportRepository dailyWorkReportRepository;

	@Override
	public List<DailyWorkReport> getIsActive(String empId) throws Exception {
		List<DailyWorkReport> dailyWorkreport = dailyWorkReportRepository.getIsActive(empId);
		try {
			if (!dailyWorkreport.isEmpty()) {
				return dailyWorkreport;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display daily work report list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public DailyWorkReport findByFullName(String fullName) throws Exception {
		Optional<DailyWorkReport> report = dailyWorkReportRepository.findByFullName(fullName);
		DailyWorkReport dailyWorkReport = null;
		try {
			if (report.isPresent()) {
				dailyWorkReport = report.get();
			}
		} catch (Exception e) {
			LOGGER.error("------Error while found the name------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No name found for ......" + fullName);
		}
		return dailyWorkReport;
	}

	@Override
	public DailyWorkReport saveWorkReport(DailyWorkReport dailyWorkReport) throws Exception {
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();

			EmpBasicDetails getDetails = empBasicRepository.getCurrentUser(empId);
			if (Objects.nonNull(getDetails)) {
				if (dailyWorkReport.getId() == null) {
					dailyWorkReport.setCreatedDate(new Date());
					dailyWorkReport.setCreatedBy(empId);
					dailyWorkReport.setIsActive("1");
					dailyWorkReport.setEmpId(getDetails.getEmpId());
					dailyWorkReport.setTenantId(getDetails.getTenantId());
					this.dailyWorkReportRepository.save(dailyWorkReport);
				} else {
					Optional<DailyWorkReport> dailyWorkReports = dailyWorkReportRepository
							.findById(dailyWorkReport.getId());
					if (dailyWorkReports.isPresent()) {
						DailyWorkReport newReport = dailyWorkReports.get();
						newReport.setDate(dailyWorkReport.getDate());
						newReport.setOfficeInTime(dailyWorkReport.getOfficeInTime());
						newReport.setOfficeOutTime(dailyWorkReport.getOfficeOutTime());
						newReport.setProjectName(dailyWorkReport.getProjectName());
						newReport.setModule(dailyWorkReport.getModule());
						newReport.setDescription(dailyWorkReport.getDescription());
						newReport.setStatus(dailyWorkReport.getStatus());
						newReport.setAnalysis(dailyWorkReport.getAnalysis());
						newReport.setLastModifiedBy(empId);
						newReport.setLastModifiedDate(new Date());
						dailyWorkReportRepository.save(newReport);
					} else {
						return dailyWorkReport;
					}
				}
			} else {
				System.out.println("Get Employee Details is null :::");
			}
		} catch (Exception e) {
			LOGGER.error("------ Error occur while save daily work report ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record save......");
		}
		return dailyWorkReport;

	}

	@Override
	public DailyWorkReport cancelWorkReport(DailyWorkReport dailyWorkReport) throws Exception {
		try {
			Optional<DailyWorkReport> optional = dailyWorkReportRepository
					.findById(dailyWorkReport.getId());
			if (optional.isPresent()) {

				DailyWorkReport workReport = optional.get();
				workReport.setIsActive(dailyWorkReport.getIsActive());
				workReport.setIsActive("0");

				dailyWorkReportRepository.save(workReport);
			} else {
				return dailyWorkReport;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while in cancel work report------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No work report cancel......");
		}
		return dailyWorkReport;
	}

	@Override
	public DailyWorkReport findByWorkReportId(Long id) throws Exception {
		Optional<DailyWorkReport> optional = dailyWorkReportRepository.findByWorkReportId(id);
		DailyWorkReport dailyWorkReport = null;
		try {
			if (optional.isPresent()) {
				dailyWorkReport = optional.get();
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while found work report for id " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("work report not found for id :: " + id);
		}
		return dailyWorkReport;
	}

	@Override
	public List<DailyWorkReport> getInActive(String empId) throws Exception {

		List<DailyWorkReport> dailyWorkreport = dailyWorkReportRepository.getInActive(empId);
		try {
			if (!dailyWorkreport.isEmpty()) {
				return dailyWorkreport;
			}
		} catch (Exception e) {
			LOGGER.error(
					"------Error occur while display daily work report list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<DailyWorkReport> getTotalWorkReport() throws Exception {
		List<DailyWorkReport> dailyWorkReports = dailyWorkReportRepository.getTotalWorkReport();
		try {
			if (Objects.nonNull(dailyWorkReports)) {
				return dailyWorkReports;
			} else {
				System.out.println("Null Data Get :::::::: ");
			}
		} catch (Exception e) {
			LOGGER.error("Error occur while get list of daily work report " + ExceptionUtils.getStackTrace(e));
		}
		return new ArrayList<>();
	}

	@Override
	public long getAllWorkReport() throws Exception {
		try {
			return dailyWorkReportRepository.getAllWorkReport();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all daily work report of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}
}
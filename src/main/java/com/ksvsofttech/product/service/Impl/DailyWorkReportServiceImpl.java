package com.ksvsofttech.product.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksvsofttech.product.dao.DailyWorkReportDao;
import com.ksvsofttech.product.entities.DailyWorkReport;
import com.ksvsofttech.product.service.DailyWorkReportService;

@Service
public class DailyWorkReportServiceImpl implements DailyWorkReportService{
	private static final Logger LOGGER = LogManager.getLogger(DailyWorkReportServiceImpl.class);

	@Autowired
	private DailyWorkReportDao dailyWorkReportDao;
	
	@Override
	public List<DailyWorkReport> getIsActive(String empId) throws Exception {
		List<DailyWorkReport> dailyWorkreport = dailyWorkReportDao.getIsActive(empId);
		try {
			if(!dailyWorkreport.isEmpty()) {
				return dailyWorkreport;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display daily work report list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public DailyWorkReport findByFullName(String fullName) throws Exception {
		DailyWorkReport dailyWorkReport = new DailyWorkReport();
		try {
			dailyWorkReport = dailyWorkReportDao.findByFullName(fullName);
		} catch (Exception e) {
			LOGGER.error("------Error while found the name------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("No name found for ......" + fullName);
		}
		return dailyWorkReport;
	}
	
	@Override
	public DailyWorkReport saveWorkReport(DailyWorkReport dailyWorkReport) throws Exception {
		try {
			this.dailyWorkReportDao.saveWorkReport(dailyWorkReport);
		} catch (Exception e) {
			LOGGER.error("------Error occur while save daily work report------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("Daily work report not saved ....." + dailyWorkReport );
		}
		return dailyWorkReport;
	}

	@Override
	public DailyWorkReport cancelWorkReport(DailyWorkReport dailyWorkReport) throws Exception {
		try {
			dailyWorkReportDao.cancelWorkReport(dailyWorkReport);
		} catch (Exception e) {
			LOGGER.error("------Error occur while cancel work report------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No branch deactivate......");
		}
		return dailyWorkReport;
	}

	@Override
	public DailyWorkReport getWorkReportById(Long id) throws Exception {
		DailyWorkReport dailyWorkReport = new DailyWorkReport();
		try {
			dailyWorkReport = dailyWorkReportDao.findByWorkReportId(id);
		} catch (Exception e) {
			LOGGER.error("------Error while daily work report not found for id------" + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("work report not found for id :: " + id);
		}
		return dailyWorkReport;
	}

	@Override
	public List<DailyWorkReport> getInActive(String empId) throws Exception {

		List<DailyWorkReport> dailyWorkreport = dailyWorkReportDao.getInActive(empId);
		try {
			if(!dailyWorkreport.isEmpty()) {
				return dailyWorkreport;
			}
		} catch (Exception e) {
			LOGGER.error("------Error occur while display daily work report list------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No record exist......");
		}
		return new ArrayList<>();
	}

	@Override
	public List<DailyWorkReport> getTotalWorkReport() throws Exception {
		List<DailyWorkReport> dailyWorkReports = dailyWorkReportDao.getTotalWorkReport();
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
			return dailyWorkReportDao.getAllWorkReport();
		} catch (Exception e) {
			LOGGER.error("Error occuring while get all daily work report of employees in current month " + ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(" No record exist... " );
		}
	}
}
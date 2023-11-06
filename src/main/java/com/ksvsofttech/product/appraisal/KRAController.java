package com.ksvsofttech.product.appraisal;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ksvsofttech.product.entities.AuditRecord;
import com.ksvsofttech.product.entities.EmailTemplate;
import com.ksvsofttech.product.entities.EmpBasicDetails;
import com.ksvsofttech.product.entities.EmpWorkDetails;
import com.ksvsofttech.product.service.AuditRecordService;
import com.ksvsofttech.product.service.DepartmentService;
import com.ksvsofttech.product.service.EmailAndOTPService;
import com.ksvsofttech.product.service.EmailService;
import com.ksvsofttech.product.service.EmpRegistartionService;

@Controller
public class KRAController {
	private static final Logger LOGGER = LogManager.getLogger(KRAController.class);

	@Autowired
	private AuditRecordService auditRecordService;
	@Autowired
	private KRAService kraService;
	@Autowired
	private EmpKRAService empKRAService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmpRegistartionService empRegistartionService;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private EmailService emailService;
	@Autowired
	EmpKRARepository empKRARepository;

	public KRAController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	@Autowired
	private EmailAndOTPService emailAndOTPService;

	/* Active KRA List in HR */
	@GetMapping("/kraList")
	public String kraList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("kraList", kraService.getActiveKra());
		} catch (Exception e) {
			LOGGER.error("Error occuring while display add kra list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed add kra list"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Appraisal Process");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/kraList";
	}

	/* Home Page of Add New KRA in HR */
	@GetMapping("/addKRA")
	public String addKRA(@ModelAttribute("kra") KRA kra, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			// model.addAttribute("kra", kra);
			model.addAttribute("departmentList", departmentService.getIsActive());
		} catch (Exception e) {
			LOGGER.error("Error occuring while display KRA add page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed KRA home page"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Appraisal Process");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/addKRA";
	}

	/* Add New KRA Method in HR */
	@PostMapping("/saveKRA")
	public String saveKRA(@ModelAttribute("kra") KRA kra, BindingResult bindingResult, Model model, Device device,
			RedirectAttributes redirAttrs) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("departmentList", departmentService.getIsActive());
			kraService.saveOrUpdate(kra);
		} catch (Exception e) {
			LOGGER.error("Error occuring while save/update kra ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create new KRA"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Appraisal Process");
			auditRecord.setActivityCode("CREATE/UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/activeKRAList";
	}

	/* update Page of Edit Registered KRA in HR */
	@GetMapping("/updateKRA/{id}")
	public String updateKRA(@ModelAttribute("kra") KRA kra, @PathVariable("id") Long id, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			model.addAttribute("kra", kra);
			model.addAttribute("departmentList", departmentService.getIsActive());
			model.addAttribute("kra", kraService.getById(id));
		} catch (Exception e) {
			LOGGER.error("Error occuring while display KRA update page... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed KRA update page"));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Appraisal Process");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/updateKRA";
	}

	/* Delete of Registered KRA */
	@GetMapping(value = "/deleteKRA/{id}")
	public String deleteKRA(KRA kra, @PathVariable(name = "id") Long id, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			kraService.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("------Error occur while delete KRA ------" + ExceptionUtils.getStackTrace(e));
			throw new Exception("No KRA delete......" + id);
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - delete KRA Id - " + id));
			auditRecord.setMenuCode("HR Management");
			auditRecord.setSubMenuCode("Appraisal Process");
			auditRecord.setActivityCode("DELETE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/kraList";
	}

	/* All Employee Appraisal Rating Report in HR */
	@GetMapping("/appraisalList")
	public String appraisalList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails allEmp = new EmpBasicDetails();
			allEmp.setEmpId("All");
			List<EmpBasicDetails> empList = empRegistartionService.getIsActiveEmpRegList();
			empList.add(allEmp);
			Collections.sort(empList, EmpBasicDetails.Comparators.EMPLOYEEID);
			model.addAttribute("empList", empList);

			List<String> empDepId = empRegistartionService.getUniqueDepId();
			empDepId.add("All");
			// Collections.sort(empDepId);
			model.addAttribute("empDepId", empDepId);

			model.addAttribute("appraisalList", empKRAService.getAllEmpAppraisal());
		} catch (Exception e) {
			LOGGER.error("Error occuring while display kra list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed appraisal list"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Appraisal Report");
			auditRecord.setActivityCode("VIEW REPORT");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/hrAppraisalList";
	}

	@GetMapping("/empIdAndTenantId")
	public String empIdAndTenantId(@ModelAttribute EmpKRA empKRA, @RequestParam String empId,
			@RequestParam String depId, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails allEmp = new EmpBasicDetails();
			allEmp.setEmpId("All");
			List<EmpBasicDetails> empList = empRegistartionService.getIsActiveEmpRegList();
			empList.add(allEmp);
			Collections.sort(empList, EmpBasicDetails.Comparators.EMPLOYEEID);
			model.addAttribute("empList", empList);

			List<String> empDepId = empRegistartionService.getUniqueDepId();
			empDepId.add("All");
			// Collections.sort(empDepId);
			model.addAttribute("empDepId", empDepId);

			if (!empId.isEmpty() && empId != null) {
				System.out.println("If EmpId Select ::::::::: ");
				List<EmpKRA> selectEmpId = empKRAService.findEmpByEmpId(empId);
				model.addAttribute("appraisalList", selectEmpId);
			} else if (!depId.isEmpty() && depId != null) {
				System.out.println("DepartementId Select ::::::::: ");
				List<EmpKRA> selectDepId = empKRAService.findEmpByDepId(depId);
				model.addAttribute("appraisalList", selectDepId);
			}
			// model.addAttribute("appraisalList", empKRAService.getAllEmpAppraisal());
		} catch (Exception e) {
			LOGGER.error("Error occuring while display active kra list... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - dispaly search appraisal data"));
			auditRecord.setMenuCode("Report");
			auditRecord.setSubMenuCode("Appraisal Report");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/hrAppraisalList";
	}

	/*---------------------------------------------------------------------------------------------------------------------------*/

	public List<String> getDateOfJoining() throws Exception {
		List<String> dojEmp = new ArrayList<>();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			if (empId != null) {
				List<String> empIds = empRegistartionService.getAllEmpId(empId);
				if (empIds != null) {
					List<EmpWorkDetails> allEmpjoiningDate = empRegistartionService.getAllEmpJoiningDate(empIds);
					if (allEmpjoiningDate != null) {

						for (int i = 0; i < allEmpjoiningDate.size(); i++) {
							String allEmpDOJ = allEmpjoiningDate.get(i).getDateOfJoining();

							Date startDate = Calendar.getInstance().getTime();
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String strDate = dateFormat.format(startDate);

							LocalDateTime localDateTime = LocalDateTime.parse(allEmpDOJ);
							LocalDate localDate = localDateTime.toLocalDate();
							Date endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
							String strDate2 = dateFormat.format(endDate);

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate date1 = LocalDate.parse(strDate2, formatter);
							LocalDate date2 = LocalDate.parse(strDate, formatter);

							Period p = Period.between(date1, date2);

							dojEmp.add(p.getYears() + " Year(s) " + p.getMonths() + " Month(s)");
							System.out.println("DATE OF JOINING :::: " + dojEmp);
						}
					} else {
						System.out.println("List of EmpWorkDetails is null :::::: " + allEmpjoiningDate);
					}
				} else {
					System.out.println("List of Employee Id's is null :::::::: " + empIds);
				}
			} else {
				System.out.println("EmpId is null :::::::: " + empId);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while get employee date of joining ... " + ExceptionUtils.getStackTrace(e));
		}
		return dojEmp;
	}

	/* Teammates List */
	@GetMapping("/teammatesList")
	public String teammatesList(@ModelAttribute EmpKRA empKRA, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();
			List<EmpWorkDetails> userDetails = null;
			if (empId != null) {
				userDetails = empKRAService.getEmpWithManger(empId);
				if (userDetails != null) {
					model.addAttribute("empList", userDetails);

					List<String> dateOfJoining = getDateOfJoining();
					System.out.println("Employee DateOfJoining  ::::: " + dateOfJoining);
					model.addAttribute("dateOfJoining", dateOfJoining);
				} else {
					System.out.println("Employee data not get ::::::");
				}
			} else {
				System.out.println("Current employee is not present ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while display teammates list ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - dispaly teammates list"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/teammatesList";
	}

	/* Tag New KRA List by HR to Manager */
	@GetMapping("/tagKRAReqList/{empId}")
	public String tagKRAReqList(@ModelAttribute EmpKRA empKRA, @PathVariable(name = "empId") String empId, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails basicDetails = empRegistartionService.getCurrentUser(empId);
			model.addAttribute("currentUser", basicDetails);

			Optional<EmpBasicDetails> empBasicDetails = empRegistartionService.findById(empId);
			if (empBasicDetails.isPresent()) {
				EmpBasicDetails empBasicDetails2 = empBasicDetails.get();
				model.addAttribute("empBasicDetailsList", empBasicDetails2);
			}

			Optional<EmpKRA> optional = empKRAService.findByempId(empId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empList", empKRA2);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while display tag KRA Req List ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammate tag KRA list "));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/tagKRAReqList";
	}

	/* Tag KRA Home Page */
	@GetMapping("/tagKraHome/{empId}")
	public String tagKraHome(@ModelAttribute EmpKRA empKRA, @PathVariable("empId") String empId, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Optional<EmpBasicDetails> optional = empRegistartionService.findById(empId);
			if (optional.isPresent()) {
				EmpBasicDetails empBasicDetail = optional.get();
				model.addAttribute("empBasicDetail", empBasicDetail);
			}

			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					String depId = userDetails.getDepartName();
					model.addAttribute("kraList", kraService.getKRAWithDepIdAndEmpId(depId));
				} else {
					System.out.println("Employee data not get ::::::");
				}
			} else {
				System.out.println("Current employee is not present ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while display tag KRA home page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammate tag KRA home page "));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/tagKRA";
	}

	/* Save Tag KRA */
	@PostMapping("/tagKRA")
	public String tagKra(@ModelAttribute EmpKRA empKRA, Model model, RedirectAttributes redirAttrs, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpKRA getEmKra = empKRAService.getKRAWithDepIdAndEmpId(empKRA.getDepId(), empKRA.getUserId());

			if (empKRAService.empKRAExists(empKRA.getKraI(), empKRA.getKraII(), empKRA.getKraIII(), empKRA.getKraIV(),
					empKRA.getKraV(), empKRA.getKraVI(), empKRA.getKraVII(), empKRA.getKraVIII(), empKRA.getKraIX(),
					empKRA.getKraX())) {
				redirAttrs.addFlashAttribute("duplicate", "This kra is already exist....");
			} else {
				if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && getEmKra.getWeightageVI() != null
						&& getEmKra.getWeightageVII() != null && getEmKra.getWeightageVIII() != null
						&& getEmKra.getWeightageIX() != null && getEmKra.getWeightageX() != null
						&& empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII().add(getEmKra.getWeightageIII().add(getEmKra.getWeightageIV()
									.add(getEmKra.getWeightageV().add(getEmKra.getWeightageVI()
											.add(getEmKra.getWeightageVII().add(getEmKra.getWeightageVIII().add(getEmKra
													.getWeightageIX()
													.add(getEmKra.getWeightageX().add(empKRA.getWeightageI()))))))))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 10 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && getEmKra.getWeightageVI() != null
						&& getEmKra.getWeightageVII() != null && getEmKra.getWeightageVIII() != null
						&& getEmKra.getWeightageIX() != null && empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII().add(getEmKra.getWeightageIII()
									.add(getEmKra.getWeightageIV().add(getEmKra.getWeightageV()
											.add(getEmKra.getWeightageVI().add(getEmKra.getWeightageVII().add(getEmKra
													.getWeightageVIII()
													.add(getEmKra.getWeightageIX().add(empKRA.getWeightageI())))))))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 9 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && getEmKra.getWeightageVI() != null
						&& getEmKra.getWeightageVII() != null && getEmKra.getWeightageVIII() != null
						&& empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII()
									.add(getEmKra.getWeightageIII().add(getEmKra.getWeightageIV()
											.add(getEmKra.getWeightageV().add(getEmKra.getWeightageVI().add(getEmKra
													.getWeightageVII()
													.add(getEmKra.getWeightageVIII().add(empKRA.getWeightageI()))))))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 8 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && getEmKra.getWeightageVI() != null
						&& getEmKra.getWeightageVII() != null && empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII()
									.add(getEmKra.getWeightageIII()
											.add(getEmKra.getWeightageIV().add(getEmKra.getWeightageV().add(getEmKra
													.getWeightageVI()
													.add(getEmKra.getWeightageVII().add(empKRA.getWeightageI())))))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 7 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && getEmKra.getWeightageVI() != null
						&& empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII()
									.add(getEmKra.getWeightageIII()
											.add(getEmKra.getWeightageIV().add(getEmKra.getWeightageV()
													.add(getEmKra.getWeightageVI().add(empKRA.getWeightageI()))))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 6 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& getEmKra.getWeightageV() != null && empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI().add(getEmKra.getWeightageII().add(getEmKra
							.getWeightageIII()
							.add(getEmKra.getWeightageIV().add(getEmKra.getWeightageV().add(empKRA.getWeightageI())))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 5 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && getEmKra.getWeightageIV() != null
						&& empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI().add(getEmKra.getWeightageII().add(
							getEmKra.getWeightageIII().add(getEmKra.getWeightageIV().add(empKRA.getWeightageI()))));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 4 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& getEmKra.getWeightageIII() != null && empKRA.getWeightageI() != null) {

					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII().add(getEmKra.getWeightageIII().add(empKRA.getWeightageI())));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 3 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && getEmKra.getWeightageII() != null
						&& empKRA.getWeightageI() != null) {
					BigDecimal totalWeightage = getEmKra.getWeightageI()
							.add(getEmKra.getWeightageII().add(empKRA.getWeightageI()));
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 2 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				} else if (getEmKra.getWeightageI() != null && empKRA.getWeightageI() != null) {
					BigDecimal totalWeightage = getEmKra.getWeightageI().add(empKRA.getWeightageI());
					double d = totalWeightage.doubleValue();
					LOGGER.info("TOTAL WEIGHTAGE 1 ::: " + totalWeightage);
					if (empKRA.getKraX() != null || d > 100.00) {
						LOGGER.info("KRA full :::::::::::: ");
						redirAttrs.addFlashAttribute("full",
								"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
					} else {
						LOGGER.info("KRA not full :::::::::::: ");
						empKRAService.saveTagKra(empKRA);
						redirAttrs.addFlashAttribute("success", "Save Successfully");
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while save tag kra  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammate create tag KRA"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/teammatesList";
	}

	/* Update Tag KRA Home Page */
	@GetMapping("/updateTagKra/{userId}")
	public String updateTagKra(@ModelAttribute EmpKRA empKRA, @PathVariable("userId") String userId, Model model,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			EmpBasicDetails userDetails = null;
			if (userId != null) {
				userDetails = empRegistartionService.getCurrentUser(userId);
				if (userDetails != null) {
					String depId = userDetails.getDepartName();
					model.addAttribute("kraList", kraService.getKRAWithDepIdAndEmpId(depId));
					model.addAttribute("kraList2", empKRAService.getKRAWithDepIdAndEmpId(depId, userId));
				} else {
					System.out.println("Employee data not get ::::::");
				}
			} else {
				System.out.println("Current employee is not present ::::::");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while display update tag KRA home page ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammate update tag KRA home page "));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/updateTagKra";
	}

	/* Update Tag KRA */
	@PostMapping("/updateTagKRA")
	public String updateTagKRA(@ModelAttribute EmpKRA empKRA, Model model, RedirectAttributes redirAttrs, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
//			if (empKRAService.empKRAExists(empKRA.getKraI(), empKRA.getKraII(), empKRA.getKraIII(), empKRA.getKraIV(),
//					empKRA.getKraV(), empKRA.getKraVI(), empKRA.getKraVII(), empKRA.getKraVIII(), empKRA.getKraIX(),
//					empKRA.getKraX())) {
//				redirAttrs.addFlashAttribute("duplicate", "This kra is already exist....");
//			} else {
			if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null && empKRA.getWeightageIII() != null
					&& empKRA.getWeightageIV() != null && empKRA.getWeightageV() != null
					&& empKRA.getWeightageVI() != null && empKRA.getWeightageVII() != null
					&& empKRA.getWeightageVIII() != null && empKRA.getWeightageIX() != null
					&& empKRA.getWeightageX() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII().add(empKRA.getWeightageIII()
								.add(empKRA.getWeightageIV().add(empKRA.getWeightageV().add(empKRA.getWeightageVI()
										.add(empKRA.getWeightageVII().add(empKRA.getWeightageVIII()
												.add(empKRA.getWeightageIX().add(empKRA.getWeightageX())))))))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 10 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null
					&& empKRA.getWeightageV() != null && empKRA.getWeightageVI() != null
					&& empKRA.getWeightageVII() != null && empKRA.getWeightageVIII() != null
					&& empKRA.getWeightageIX() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII()
								.add(empKRA.getWeightageIII().add(empKRA.getWeightageIV().add(
										empKRA.getWeightageV().add(empKRA.getWeightageVI().add(empKRA.getWeightageVII()
												.add(empKRA.getWeightageVIII().add(empKRA.getWeightageIX()))))))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 9 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null
					&& empKRA.getWeightageV() != null && empKRA.getWeightageVI() != null
					&& empKRA.getWeightageVII() != null && empKRA.getWeightageVIII() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII()
								.add(empKRA.getWeightageIII().add(
										empKRA.getWeightageIV().add(empKRA.getWeightageV().add(empKRA.getWeightageVI()
												.add(empKRA.getWeightageVII().add(empKRA.getWeightageVIII())))))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 8 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null
					&& empKRA.getWeightageV() != null && empKRA.getWeightageVI() != null
					&& empKRA.getWeightageVII() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII().add(empKRA.getWeightageIII().add(empKRA.getWeightageIV().add(
								empKRA.getWeightageV().add(empKRA.getWeightageVI().add(empKRA.getWeightageVII()))))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 7 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null
					&& empKRA.getWeightageV() != null && empKRA.getWeightageVI() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII().add(empKRA.getWeightageIII().add(
								empKRA.getWeightageIV().add(empKRA.getWeightageV().add(empKRA.getWeightageVI())))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 6 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null
					&& empKRA.getWeightageV() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI().add(empKRA.getWeightageII()
						.add(empKRA.getWeightageIII().add(empKRA.getWeightageIV().add(empKRA.getWeightageV()))));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 5 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null && empKRA.getWeightageIV() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII().add(empKRA.getWeightageIII().add(empKRA.getWeightageIV())));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 4 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null
					&& empKRA.getWeightageIII() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI()
						.add(empKRA.getWeightageII().add(empKRA.getWeightageIII()));
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 3 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null && empKRA.getWeightageII() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI().add(empKRA.getWeightageII());
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 2 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			} else if (empKRA.getWeightageI() != null) {
				BigDecimal totalWeightage = empKRA.getWeightageI();
				double d = totalWeightage.doubleValue();
				LOGGER.info("TOTAL WEIGHTAGE 1 ::: " + totalWeightage);
				if (empKRA.getKraX() != null || d > 100.00) {
					LOGGER.info("KRA full :::::::::::: ");
					redirAttrs.addFlashAttribute("full",
							"Maximum(10) KRA Allotted OR total weightage is greater then 100%");
				} else {
					LOGGER.info("KRA not full :::::::::::: ");
					empKRAService.updateTagKra(empKRA);
					redirAttrs.addFlashAttribute("success", "Update Successfully");
				}
			}
			// }
		} catch (Exception e) {
			LOGGER.error("Error occuring while update tag kra  ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammate update tag KRA"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Teammates");
			auditRecord.setActivityCode("UPDATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/teammatesList";
	}

	/*---------------------------------------------------------------------------------------------------------------------------*/

	/* Employee Self Appraisal List */
	@GetMapping("/selfAppraisalList")
	public String selfAppraisalList(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();

			if (Objects.nonNull(empId)) {
				EmpBasicDetails basicDetails = empRegistartionService.getCurrentUser(empId);
				model.addAttribute("currentUser", basicDetails);

				List<EmpBasicDetails> empBasicDetails = empRegistartionService.listOfCurrentUser(empId);
				model.addAttribute("getEmpId", empBasicDetails);

				List<EmpKRA> empKRA = empKRAService.getCurrentEmpAppraisal(empId);
				model.addAttribute("appraisalList", empKRA);

				List<EmpKRA> empKRA2 = empKRAService.appraisalCycleList(empId);
				model.addAttribute("appraisalCycleList", empKRA2);

				LocalDate currentdate = LocalDate.now();
				String month = currentdate.format(DateTimeFormatter.ofPattern("MM"));
				String year = currentdate.format(DateTimeFormatter.ofPattern("YYYY"));
				int nextYear = currentdate.getYear() + 1;

				if ("10".equals(month) || "11".equals(month) || "12".equals(month) || "01".equals(month)
						|| "02".equals(month) || "03".equals(month)) {
					model.addAttribute("month", "Appraisal Cycle October " + year + " to March " + nextYear);
				} else {
					System.out.println("Else Month ::::: " + month);
					model.addAttribute("month", "Appraisal Cycle April " + year + " to September " + year);
				}

			} else {
				System.out.println("Current empId is not present");
			}
		} catch (Exception e) {
			LOGGER.error("Error Ocuring while display current employee appraisal " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - viewed appraisal list"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Performance");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/myreview";
	}

	/* Employee Self Appraisal Home Page */
	@GetMapping("/selfAppraisal/{userId}")
	public String selfAppraisal(@ModelAttribute EmpKRA empKRA, @PathVariable String userId, Model model, Device device)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {

			Optional<EmpKRA> optional = empKRAService.findByempId(userId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empKRA", empKRA2);
			}

			EmpBasicDetails userDetails = null;
			if (userId != null) {
				userDetails = empRegistartionService.getCurrentUser(userId);
				if (userDetails != null) {
					model.addAttribute("userDetail", userDetails);
					String depId = userDetails.getDepartName();

					EmpKRA empKRAList = empKRAService.getKRAWithDepIdAndEmpId(depId, userId);
					System.out.println("IMAGE ::: " + empKRAList.getEmpBasicDetails().getFile());
					model.addAttribute("kraList", empKRAList);
				} else {
					System.out.println("Employee data not get ::::::");
				}
			} else {
				System.out.println("Current employee is not present ::::::");
			}

			LocalDate currentdate = LocalDate.now();
			String month = currentdate.format(DateTimeFormatter.ofPattern("MM"));
			String year = currentdate.format(DateTimeFormatter.ofPattern("YYYY"));
			int nextYear = currentdate.getYear() + 1;

			if ("10".equals(month) || "11".equals(month) || "12".equals(month) || "01".equals(month)
					|| "02".equals(month) || "03".equals(month)) {
				model.addAttribute("month", "Appraisal Cycle October " + year + " to March " + nextYear);
			} else {
				System.out.println("Else Month ::::: " + month);
				model.addAttribute("month", "Appraisal Cycle April " + year + " to September " + year);
			}
		} catch (Exception e) {
			LOGGER.error("Error Ocuring while display add self appraisal home page " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view self appraisal home page"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Performance");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/selfAppraisal";
	}

	/* Employee Self Appraisal Save & Save Draft */
	@PostMapping(value = "/saveAppraisal")
	public String saveAppraisal(@ModelAttribute EmpKRA empKRA, Model model, Device device,
			RedirectAttributes redirAttrs, @RequestParam(value = "action", required = true) String action)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		EmailTemplate emailTemplate = emailService.getSelfAppraisal();
		try {
			if (action.equals("saveDraft")) {
				System.out.println("SAVE DRAFT ::::::::");
				empKRAService.saveDraftSelfAppraisal(empKRA);
			}

			if (action.equals("save")) {
				System.out.println("SAVE ::::::::");
				empKRAService.saveSelfAppraisal(empKRA);

				/* Self Appraisal Complete Mail send to Reporting Manager */
				List<EmpKRA> empKRAList = null;
				if (Objects.nonNull(empKRA)) {
					empKRAList = empKRAService.getCurrentEmpAppraisal(empKRA.getUserId());
					if (Objects.nonNull(empKRAList)) {
						Context context = new Context();
						context.setVariable("empKRAList", empKRAList);
						String body = templateEngine.process("appraisal/selfAppraisalMail", context);
						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						emailAndOTPService.emailSend(from, to, subject, body, cc);
					} else {
						System.out.println("Nothing Happen ::::::");
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			}
			redirAttrs.addFlashAttribute("success", "Save Successfully");
			return "redirect:/selfAppraisalList";
		} catch (Exception e) {
			LOGGER.error("Error occuring while save self rating ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create self appraisal "));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Performance");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/selfAppraisalList";
	}

	/* Employee Self Appraisal Home Page */
	@GetMapping("/previousAppraisal/{empId}/{date}")
	public String previousAppraisal(@ModelAttribute EmpKRA empKRA, @PathVariable String empId,
			@PathVariable String date, Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {

			Optional<EmpKRA> optional = empKRAService.findByempId(empId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empKRA", empKRA2);
			}

			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("userDetail", userDetails);
					String depId = userDetails.getDepartName();

					EmpKRA empKRAList2 = empKRAService.getKRAWithDepIdAndEmpIdAndDate(depId, empId, date);
					model.addAttribute("kraList", empKRAList2);
				} else {
					System.out.println("Employee data not get ::::::");
				}
			} else {
				System.out.println("Current employee is not present ::::::");
			}
		} catch (Exception e) {
			LOGGER.error("Error Ocuring while display previous appraisal page " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - view previous appraisal"));
			auditRecord.setMenuCode("Employee Management");
			auditRecord.setSubMenuCode("Performance");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/previousAppraisal";
	}

	/*---------------------------------------------------------------------------------------------------------------------------*/

	/* Display List of Employee Appraisal By Manager */
	@GetMapping(value = "/empAppraisalByManager")
	public String empAppraisalByManager(Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String empId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			empId = authentication.getName();

			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					String depId = userDetails.getDepartName();

					List<String> empIds = empKRARepository.getTeammetsTeamMangerId2(empId, depId);
					List<EmpKRA> empKRA = empKRAService.getEmpWithMangerIdOrReportingManager(empId, depId, empIds);
					model.addAttribute("appraisal", empKRA);
				} else {
					System.out.println("No Employee record Found ::::::::::");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while display rating list to manager " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - appraisal rating list "));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/appraisalList";
	}

	/* Teammet Review Pages */
	@GetMapping(value = "/teammetReview/{empId}")
	public String teammetReview(@ModelAttribute EmpKRA empKRA, Model model, @PathVariable(name = "empId") String empId,
			Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		String userId;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userId = authentication.getName();
			Optional<EmpKRA> optional = empKRAService.findByempId(empId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empKRA", empKRA2);

				if (Objects.nonNull(empId)) {

					EmpBasicDetails basicDetailsOfEmp = empRegistartionService.getCurrentUser(empId);
					model.addAttribute("currentUser", basicDetailsOfEmp);

					model.addAttribute("appraisalCycleList", empKRAService.appraisalCycleList(empId));
					model.addAttribute("appraisalList", empKRAService.getCurrentEmpAppraisal(empId));

					EmpBasicDetails empBasicDetails = empRegistartionService.getKRAWithDepIdAndEmpId(empKRA2.getDepId(),
							empId);
					String managerId = empBasicDetails.getEmpWorkDetails().getReportingManager();
					EmpBasicDetails basicDetails = empRegistartionService.getCurrentUser(managerId);
					model.addAttribute("managerDetails", basicDetails);

					EmpBasicDetails empBasicDetails2 = empRegistartionService
							.getKRAWithDepIdAndEmpId(empKRA2.getDepId(), managerId);
					String managerId2 = empBasicDetails2.getEmpWorkDetails().getReportingManager();
					EmpBasicDetails basicDetails2 = empRegistartionService.getCurrentUser(managerId2);
					model.addAttribute("managerDetails2", basicDetails2);

					model.addAttribute("userId", userId);

					LocalDate currentdate = LocalDate.now();
					String month = currentdate.format(DateTimeFormatter.ofPattern("MM"));
					String year = currentdate.format(DateTimeFormatter.ofPattern("YYYY"));
					int nextYear = currentdate.getYear() + 1;

					if ("10".equals(month) || "11".equals(month) || "12".equals(month) || "01".equals(month)
							|| "02".equals(month) || "03".equals(month)) {
						model.addAttribute("month", "Appraisal Cycle October " + year + " to March " + nextYear);
					} else {
						System.out.println("Else Month ::::: " + month);
						model.addAttribute("month", "Appraisal Cycle April " + year + " to September " + year);
					}
				} else {
					System.out.println("Current empId is not present");
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while give rating by manager to employee " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - teammates review"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/teammetReview";
	}

	/* Leave 1 Rating Home Page */
	@GetMapping(value = "/ratingByReportingManager/{userId}")
	public String ratingByReportingManager(@ModelAttribute EmpKRA empKRA, Model model,
			@PathVariable(name = "userId") String userId, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Optional<EmpKRA> optional = empKRAService.findByempId(userId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empKRA", empKRA2);
			}

			EmpBasicDetails userDetails = null;
			if (userId != null) {
				userDetails = empRegistartionService.getCurrentUser(userId);
				if (userDetails != null) {
					model.addAttribute("userDetail", userDetails);
					String depId = userDetails.getDepartName();

					EmpKRA empKRAList = empKRAService.getKRAWithDepIdAndEmpId(depId, userId);
					model.addAttribute("kraList", empKRAList);

					EmpBasicDetails managerList = empRegistartionService.getKRAWithDepIdAndEmpId(depId,
							userDetails.getEmpWorkDetails().getReportingManager());
					model.addAttribute("managerList", managerList);
				}
			}

			LocalDate currentdate = LocalDate.now();
			String month = currentdate.format(DateTimeFormatter.ofPattern("MM"));
			String year = currentdate.format(DateTimeFormatter.ofPattern("YYYY"));
			int nextYear = currentdate.getYear() + 1;

			if ("10".equals(month) || "11".equals(month) || "12".equals(month) || "01".equals(month)
					|| "02".equals(month) || "03".equals(month)) {
				model.addAttribute("month", "Appraisal Cycle October " + year + " to March " + nextYear);
			} else {
				System.out.println("Else Month ::::: " + month);
				model.addAttribute("month", "Appraisal Cycle April " + year + " to September " + year);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while give rating by manager to employee " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - level 1 appraisal rating home page"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/ratingByReportingManager";
	}

	/* Leave 1 Rating by Manager Save & Save Draft */
	@PostMapping(value = "/saveRatingByReportingManager")
	public String saveRatingByReportingManager(@ModelAttribute EmpKRA empKRA, Model model, Device device,
			RedirectAttributes redirAttrs, @RequestParam(value = "action", required = true) String action)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		EmailTemplate emailTemplate = emailService.getRatingByRM();
		try {
			if (action.equals("saveDraft")) {
				empKRAService.saveDraftRatingByReportingManager(empKRA);
			}
			if (action.equals("save")) {
				empKRAService.saveRatingByReportingManager(empKRA);

				/* Rating by Reporting Manager to employee */
				List<EmpKRA> empKRAList = null;
				if (Objects.nonNull(empKRA)) {
					empKRAList = empKRAService.getCurrentEmpAppraisal(empKRA.getUserId());
					if (Objects.nonNull(empKRAList)) {
						Context context = new Context();
						context.setVariable("empKRAList", empKRAList);
						String body = templateEngine.process("appraisal/ratingByRMMail", context);
						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						emailAndOTPService.emailSend(from, to, subject, body, cc);
					} else {
						System.out.println("Nothing Happen ::::::");
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			}
			redirAttrs.addFlashAttribute("success", "Save Successfully");
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while save rating by manager to employee ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create level 1 appraisal rating"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/empAppraisalByManager";
	}

	/* Leave 2 Rating Home Page */
	@GetMapping(value = "/ratingByReportingManager2/{empId}")
	public String ratingByReportingManager2(@ModelAttribute EmpKRA empKRA, @PathVariable(name = "empId") String empId,
			Model model, Device device) throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		try {
			Optional<EmpKRA> optional = empKRAService.findByempId(empId);
			if (optional.isPresent()) {
				EmpKRA empKRA2 = optional.get();
				model.addAttribute("empKRA", empKRA2);
			}

			EmpBasicDetails userDetails = null;
			if (empId != null) {
				userDetails = empRegistartionService.getCurrentUser(empId);
				if (userDetails != null) {
					model.addAttribute("userDetail", userDetails);
					String depId = userDetails.getDepartName();
					String managerId = userDetails.getEmpWorkDetails().getReportingManager();

					EmpKRA empKRAList = empKRAService.getManagerIdWithMangerIdWithDepId(managerId, depId, empId);
					model.addAttribute("kraList", empKRAList);

					EmpBasicDetails managerList = empRegistartionService.getKRAWithDepIdAndEmpId(depId,
							userDetails.getEmpWorkDetails().getReportingManager());
					model.addAttribute("managerList", managerList);

					String seniorManger = managerList.getEmpWorkDetails().getReportingManager();
					EmpBasicDetails managerList2 = empRegistartionService.getKRAWithDepIdAndEmpId(depId, seniorManger);
					model.addAttribute("managerList2", managerList2);
				}
			}

			LocalDate currentdate = LocalDate.now();
			String month = currentdate.format(DateTimeFormatter.ofPattern("MM"));
			String year = currentdate.format(DateTimeFormatter.ofPattern("YYYY"));
			int nextYear = currentdate.getYear() + 1;

			if ("10".equals(month) || "11".equals(month) || "12".equals(month) || "01".equals(month)
					|| "02".equals(month) || "03".equals(month)) {
				model.addAttribute("month", "Appraisal Cycle October " + year + " to March " + nextYear);
			} else {
				System.out.println("Else Month ::::: " + month);
				model.addAttribute("month", "Appraisal Cycle April " + year + " to September " + year);
			}
		} catch (Exception e) {
			LOGGER.error("Error occuring while give rating by manager to employee " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - level 2 appraisal rating home page"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("VIEW");
			auditRecordService.save(auditRecord, device);
		}
		return "appraisal/ratingByReportingManager2";
	}

	/* Leave 2 Rating by Senior Manager Save & Save Draft */
	@PostMapping(value = "/saveRatingByReportingManager2")
	public String saveRatingByReportingManager2(@ModelAttribute EmpKRA empKRA, Model model, Device device,
			RedirectAttributes redirAttrs, @RequestParam(value = "action", required = true) String action)
			throws Exception {
		AuditRecord auditRecord = new AuditRecord();
		EmailTemplate emailTemplate = emailService.getRatingByManager();
		try {

			if (action.equals("saveDraft")) {
				empKRAService.saveDraftRatingByReportingManager2(empKRA);
			}
			if (action.equals("save")) {
				empKRAService.saveRatingByReportingManager2(empKRA);

				/* Rating by Manager to employee */
				List<EmpKRA> empKRAList = null;
				if (Objects.nonNull(empKRA)) {
					empKRAList = empKRAService.getCurrentEmpAppraisal(empKRA.getUserId());
					if (Objects.nonNull(empKRAList)) {
						Context context = new Context();
						context.setVariable("empKRAList", empKRAList);
						String body = templateEngine.process("appraisal/ratingByManagerMail", context);
						String from = emailTemplate.getEmailFrom();
						String to = emailTemplate.getEmailTo();
						String subject = emailTemplate.getEmailSub();
						String cc = emailTemplate.getEmailCc();

						emailAndOTPService.emailSend(from, to, subject, body, cc);
					} else {
						System.out.println("Nothing Happen ::::::");
					}
				} else {
					System.out.println("Nothing Happen ::::::");
				}
			}
			redirAttrs.addFlashAttribute("success", "Save Successfully");
		} catch (Exception e) {
			LOGGER.error(
					"Error occuring while save rating by manager to employee ... " + ExceptionUtils.getStackTrace(e));
		} finally {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			auditRecord.setRemarks(userDetails.getUsername().concat(" - create level 2 appraisal rating"));
			auditRecord.setMenuCode("Organization");
			auditRecord.setSubMenuCode("Appraisee View");
			auditRecord.setActivityCode("CREATE");
			auditRecordService.save(auditRecord, device);
		}
		return "redirect:/empAppraisalByManager";
	}
}
package org.medtech.wui.controller;

import javax.inject.Inject;

import org.medtech.domain.modules.medtech.Heart;
import org.medtech.domain.modules.medtech.Patient;
import org.medtech.domain.modules.medtech.PatientSearchCriteria;
import org.medtech.fw.presentation.BaseController;
import org.medtech.presentation.form.AccountForm;
import org.medtech.service.modules.medtech.HeartService;
import org.medtech.service.modules.medtech.PatientService;
import org.medtech.wui.form.PatientSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value="/patient")
@SessionAttributes()
public class PatientController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	@Inject 
	PatientService patientService;
	
	@Inject 
	HeartService heartService;
	
	@RequestMapping(value={"/show-info"}, method = RequestMethod.GET)
	public String showPatientInfo(Long patientId, Model model){
		Patient patient = patientService.getPatientById(patientId);
		model.addAttribute("account", mapper.map(patient,AccountForm.class));
		return "account/FiAccountInfo";
	}
	
	@RequestMapping(value={"/show-vital/{patientId}"}, method = RequestMethod.GET)
	public String showVitalReport(@PathVariable("patientId") Long patientId, Model model){
		Patient patient = patientService.getPatientById(patientId);
		Heart heart = heartService.getLastHeartByPatientId(patientId);
		model.addAttribute("patient", patient);
		model.addAttribute("heart", heart);
		return "medtech/FiVitalSign";
	}
	
	@RequestMapping(value={"/search"}, method = RequestMethod.GET)
	public String displayPatientSearch(PatientSearchForm patientSearchForm, Model model, @PageableDefault Pageable pageable){
		PatientSearchCriteria criteria = mapper.map(patientSearchForm, PatientSearchCriteria.class);
		Page<Patient> page = patientService.findAllPatientByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsPatient";
	}
	
	@RequestMapping(value={"/search"}, method = RequestMethod.POST)
	public String processPatientSearch(PatientSearchForm patientSearchForm,Model model, @PageableDefault Pageable pageable){
		PatientSearchCriteria criteria = mapper.map(patientSearchForm, PatientSearchCriteria.class);
		Page<Patient> page = patientService.findAllPatientByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsPatient";
	}
	
	@RequestMapping(value={"/tracking-calendar"}, method = RequestMethod.GET)
	public String displayTrackingCalendar(Model model){
		return "medtech/FiTrackingCalendar";
	}
	
	@RequestMapping(value={"/reporting"}, method = RequestMethod.GET)
	public String displayReorting(Model model){
		return "medtech/FiReporting";
	}
	
	@RequestMapping(value={"/charting"}, method = RequestMethod.GET)
	public String displayCharting(Model model){
		return "medtech/FiCharting";
	}
	
	@RequestMapping(value={"/pas"}, method = RequestMethod.GET)
	public String displayPas(Model model){
		return "medtech/FiPas";
	}
}

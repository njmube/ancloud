package org.medtech.wui.controller;

import javax.inject.Inject;

import org.medtech.domain.modules.medtech.Doctor;
import org.medtech.domain.modules.medtech.DoctorSearchCriteria;
import org.medtech.domain.modules.medtech.Heart;
import org.medtech.domain.modules.medtech.HeartSearchCriteria;
import org.medtech.domain.modules.medtech.Nurse;
import org.medtech.domain.modules.medtech.NurseSearchCriteria;
import org.medtech.domain.modules.medtech.Patient;
import org.medtech.domain.modules.medtech.PatientSearchCriteria;
import org.medtech.fw.presentation.BaseController;
import org.medtech.presentation.form.AccountForm;
import org.medtech.presentation.form.AccountFormValidator;
import org.medtech.service.modules.medtech.DoctorService;
import org.medtech.service.modules.medtech.HeartService;
import org.medtech.service.modules.medtech.NurseService;
import org.medtech.service.modules.medtech.PatientService;
import org.medtech.wui.form.DoctorSearchForm;
import org.medtech.wui.form.HeartSearchForm;
import org.medtech.wui.form.NurseSearchForm;
import org.medtech.wui.form.PatientSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value="/med")
@SessionAttributes(types={HeartSearchForm.class})
public class MedFrontController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(MedFrontController.class);
	
	@Inject 
	HeartService heartService;
	
	@Inject 
	NurseService nurseService;
	
	@Inject 
	DoctorService doctorService;
	
	@Inject
	AccountFormValidator accountFormValidator;
	
	@InitBinder("accountForm")
	public void initaccountForm(WebDataBinder binder){
		binder.addValidators(accountFormValidator);
	}
	
	
	
	@RequestMapping(value={"/nurse/search"}, method = RequestMethod.GET)
	public String displayNurseSearch(NurseSearchForm nurseSearchForm, Model model, @PageableDefault Pageable pageable){
		NurseSearchCriteria criteria = mapper.map(nurseSearchForm, NurseSearchCriteria.class);
		Page<Nurse> page = nurseService.findAllNurseByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsNurse";
	}
	
	@RequestMapping(value={"/nurse/search"}, method = RequestMethod.POST)
	public String processNurseSearch(NurseSearchForm nurseSearchForm,Model model, @PageableDefault Pageable pageable){
		NurseSearchCriteria criteria = mapper.map(nurseSearchForm, NurseSearchCriteria.class);
		Page<Nurse> page = nurseService.findAllNurseByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsNurse";
	}
	@RequestMapping(value={"/heart/search"}, method = RequestMethod.GET)
	public String displayHeartSearch(HeartSearchForm heartSearchForm, Model model, @PageableDefault(sort={"recordedDate"},direction=Sort.Direction.DESC) Pageable pageable){
		HeartSearchCriteria criteria = mapper.map(heartSearchForm, HeartSearchCriteria.class);
		Page<Heart> page = heartService.findAllHeartByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsHeart";
	}
	
	@RequestMapping(value={"/heart/search"}, method = RequestMethod.POST)
	public String processHeartSearch(HeartSearchForm heartSearchForm,Model model, @PageableDefault(sort={"recordedDate"},direction=Sort.Direction.DESC) Pageable pageable){
		HeartSearchCriteria criteria = mapper.map(heartSearchForm, HeartSearchCriteria.class);
		Page<Heart> page = heartService.findAllHeartByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsHeart";
	}
	
	@RequestMapping(value={"/doctor/search"}, method = RequestMethod.GET)
	public String displayDoctorSearch(DoctorSearchForm doctorSearchForm, Model model, @PageableDefault Pageable pageable){
		DoctorSearchCriteria criteria = mapper.map(doctorSearchForm, DoctorSearchCriteria.class);
		Page<Doctor> page = doctorService.findAllDoctorByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsDoctor";
	}
	
	@RequestMapping(value={"/doctor/search"}, method = RequestMethod.POST)
	public String processDoctorSearch(DoctorSearchForm doctorSearchForm,Model model, @PageableDefault Pageable pageable){
		DoctorSearchCriteria criteria = mapper.map(doctorSearchForm, DoctorSearchCriteria.class);
		Page<Doctor> page = doctorService.findAllDoctorByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsDoctor";
	}
}

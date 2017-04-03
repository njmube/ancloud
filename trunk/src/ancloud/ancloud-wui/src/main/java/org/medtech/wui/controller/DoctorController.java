package org.medtech.wui.controller;

import javax.inject.Inject;

import org.medtech.domain.modules.medtech.Doctor;
import org.medtech.domain.modules.medtech.DoctorSearchCriteria;
import org.medtech.fw.presentation.BaseController;
import org.medtech.service.modules.medtech.DoctorService;
import org.medtech.wui.form.DoctorSearchForm;
import org.medtech.wui.form.HeartSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value="/doctor")
@SessionAttributes(types={HeartSearchForm.class})
public class DoctorController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(DoctorController.class);
	
	@Inject 
	DoctorService doctorService;
	
	@RequestMapping(value={"/search"}, method = RequestMethod.GET)
	public String displayDoctorSearch(DoctorSearchForm doctorSearchForm, Model model, @PageableDefault Pageable pageable){
		DoctorSearchCriteria criteria = mapper.map(doctorSearchForm, DoctorSearchCriteria.class);
		Page<Doctor> page = doctorService.findAllDoctorByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsDoctor";
	}
	
	@RequestMapping(value={"/search"}, method = RequestMethod.POST)
	public String processDoctorSearch(DoctorSearchForm doctorSearchForm,Model model, @PageableDefault Pageable pageable){
		DoctorSearchCriteria criteria = mapper.map(doctorSearchForm, DoctorSearchCriteria.class);
		Page<Doctor> page = doctorService.findAllDoctorByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsDoctor";
	}
}

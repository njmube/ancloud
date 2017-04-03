package org.medtech.wui.controller;

import javax.inject.Inject;

import org.medtech.domain.modules.medtech.Nurse;
import org.medtech.domain.modules.medtech.NurseSearchCriteria;
import org.medtech.fw.presentation.BaseController;
import org.medtech.service.modules.medtech.HeartService;
import org.medtech.service.modules.medtech.NurseService;
import org.medtech.wui.form.NurseSearchForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/nurse")
public class NurseController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(NurseController.class);
	
	@Inject 
	HeartService heartService;
	
	@Inject 
	NurseService nurseService;
	
	@RequestMapping(value={"/search"}, method = RequestMethod.GET)
	public String displayNurseSearch(NurseSearchForm nurseSearchForm, Model model, @PageableDefault Pageable pageable){
		NurseSearchCriteria criteria = mapper.map(nurseSearchForm, NurseSearchCriteria.class);
		Page<Nurse> page = nurseService.findAllNurseByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsNurse";
	}
	
	@RequestMapping(value={"/search"}, method = RequestMethod.POST)
	public String processNurseSearch(NurseSearchForm nurseSearchForm,Model model, @PageableDefault Pageable pageable){
		NurseSearchCriteria criteria = mapper.map(nurseSearchForm, NurseSearchCriteria.class);
		Page<Nurse> page = nurseService.findAllNurseByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "medtech/FsNurse";
	}

}

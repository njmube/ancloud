package org.bluebird.presentation.module.account;

import javax.inject.Inject;

import ma.glasnost.orika.MapperFacade;

import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.module.account.Account;
import org.bluebird.domain.module.account.AccountSearchCriteria;
import org.bluebird.fw.core.service.SessionService;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.service.module.account.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="account")
public class AccountController {
	
	@Inject
	AccountService accountService;
	
	@Inject
	SessionService sessionService;
	
	@Inject
	MapperFacade mapper;
	
	@InitBinder(value="accountSearchForm")
	public void initSearchForm(WebDataBinder binder){
		
	}
	
	@RequestMapping(method = {RequestMethod.GET})
	public String display(){
		return "forward:/account/search";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.GET})
	public String displaySearch(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/accountSearchForm";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.GET},params={"all"})
	public String displaySearchAll(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/accountSearchForm";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/accountSearchResult";
	} 
}

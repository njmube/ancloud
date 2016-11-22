package org.bluebird.presentation.modules.account;

import javax.inject.Inject;

import ma.glasnost.orika.MapperFacade;

import org.bluebird.domain.Project;
import org.bluebird.domain.common.SessionConstant;
import org.bluebird.domain.modules.account.Account;
import org.bluebird.domain.modules.account.AccountSearchCriteria;
import org.bluebird.fw.core.service.SessionService;
import org.bluebird.fw.presentation.BaseController;
import org.bluebird.fw.presentation.resolver.Session;
import org.bluebird.service.modules.account.AccountService;
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
public class AccountController extends BaseController {
	
	@Inject
	AccountService accountService;
	
	@Inject
	SessionService sessionService;
	
	@InitBinder(value="accountSearchForm")
	public void initSearchForm(WebDataBinder binder){
		
	}
	
	
	@RequestMapping(value={"","search"}, method = {RequestMethod.GET})
	public String displaySearch(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.GET},params={"all"})
	public String displaySearchAll(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FrAccount";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		criteria.setProject(project);
		Page<Account> page = accountService.findAllAccountByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FrAccount";
	} 
}

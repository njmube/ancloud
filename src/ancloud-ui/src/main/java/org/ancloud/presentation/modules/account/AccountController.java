package org.ancloud.presentation.modules.account;

import javax.inject.Inject;

import org.ancloud.domain.Project;
import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountSearchCriteria;
import org.ancloud.fw.core.service.SessionService;
import org.ancloud.fw.presentation.BaseController;
import org.ancloud.fw.presentation.resolver.Session;
import org.ancloud.service.account.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(value="account")
@SessionAttributes(types=AccountSearchForm.class)
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
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.GET},params={"all"})
	public String displaySearchAll(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable, SessionStatus status){
		status.isComplete();
		accountSearchForm=new AccountSearchForm();
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	}
	
	@RequestMapping(value="search", method = {RequestMethod.POST})
	public String processSearch(AccountSearchForm accountSearchForm, Model model,@Session(key="project") Project project, @PageableDefault Pageable pageable){
		AccountSearchCriteria criteria = mapper.map(accountSearchForm, AccountSearchCriteria.class);
		Page<Account> page = accountService.findAllByCriteria(criteria, pageable);
		model.addAttribute("page",page);
		return "account/FsAccount";
	} 
}

package org.ancloud.wui.controller;

import javax.inject.Inject;

import org.ancloud.domain.account.Account;
import org.ancloud.domain.account.AccountSearchCriteria;
import org.ancloud.fw.presentation.controller.resolver.JsonParam;
import org.ancloud.service.account.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "autocomplete")
public class AutocompleteController {

	@Inject
	AccountService accountService;

	@RequestMapping("/account")
	public Page<Account> getAccount(@JsonParam("parameter") AccountSearchCriteria medicalCenterSearchCriteria,
			@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
		return accountService.findAllByCriteria(medicalCenterSearchCriteria, pageable);
	}
}

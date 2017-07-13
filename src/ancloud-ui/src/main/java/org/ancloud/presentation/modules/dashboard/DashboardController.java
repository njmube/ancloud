package org.ancloud.presentation.modules.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/dashboard"})
public class DashboardController {
	@RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
	public String getHome(){
		return "dashboard/dashboard";
	}
}

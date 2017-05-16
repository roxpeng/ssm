package com.rm.rox.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController
{
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(Model model)
	{
		String welcomeUrl;
		Object user =  SecurityUtils.getSubject().getPrincipal();
		if (user == null)
		{
			welcomeUrl = "/login";
		}
		else
		{
			welcomeUrl = "redirect:welcome.jsp";
		}
		return welcomeUrl;
	}
}
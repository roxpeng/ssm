package com.rm.rox.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 * @author  LiuPeng
 * @date 2015年4月23日 
 * @version 1.0
 *  
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
  @RequestMapping(method = RequestMethod.GET)
  public String login(HttpServletRequest request, Model model) {
	Object user =  SecurityUtils.getSubject().getPrincipal();
	//如果已经登陆~~
	if (user != null)
	{
		return "redirect:welcome.jsp";
	}
	model.addAttribute("message", "用户名或密码错误");
    return "login";
  }
  @RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model, HttpServletRequest request)
	{
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login";
	}
}
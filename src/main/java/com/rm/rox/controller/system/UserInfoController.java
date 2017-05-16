package com.rm.rox.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.rm.rox.bean.UserInfo;
import com.rm.rox.service.IUserInfoService;
import com.rm.rox.utils.Digests;
import com.rm.rox.utils.Encodes;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	private static Logger logger = Logger.getLogger(UserInfoController.class);

	@Resource
	private IUserInfoService userService;

	@RequestMapping("/showUser")
	public String testUser(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("id"));

		UserInfo user = this.userService.getUserById(userId);
		
		logger.info(JSON.toJSONString(user));
		
		model.addAttribute("user", user);
		return "index";
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public static void entryptPassword(UserInfo userInfo) {
		byte[] salt = Digests.generateSalt(8);
		userInfo.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(userInfo.getPassword().getBytes(), salt, 1024);
		userInfo.setPassword(Encodes.encodeHex(hashPassword));
	}
}

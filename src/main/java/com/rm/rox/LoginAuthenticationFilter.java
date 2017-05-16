package com.rm.rox;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Service;

/** 
 * @author  LiuPeng
 * @date 2016年11月18日 
 * @version 1.0
 *  
 */

@Service
public class LoginAuthenticationFilter extends FormAuthenticationFilter
{
	@Override
	protected boolean onLoginFailure(AuthenticationToken authcToken, AuthenticationException e,
			ServletRequest request, ServletResponse response)
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	
		return super.onLoginFailure(token, e, request, response);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken authcToken, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		return super.onLoginSuccess(token, subject, request, response);
	}
}

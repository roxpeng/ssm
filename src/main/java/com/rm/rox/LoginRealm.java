package com.rm.rox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.rm.rox.bean.UserInfo;
import com.rm.rox.service.IUserInfoService;
import com.rm.rox.utils.Encodes;

public class LoginRealm extends AuthorizingRealm {
	@Resource
	private IUserInfoService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
		UserInfo user = userService.getUserByLoginName(shiroUser.getLoginName());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> roles = new ArrayList<String>();
		roles.add(user.getRoleId().toString());
		info.addRoles(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// System.out.println("验证当前Subject时获取到token为" +
		// ReflectionToStringBuilder.toString(token,
		// ToStringStyle.MULTI_LINE_STYLE));
		UserInfo user = userService.getUserByLoginName(token.getUsername());
		List<String> roles = new ArrayList<String>();
		roles.add(user.getRoleId().toString());
		if (user != null && user.getFlag() == 1) {
			byte[] salt = Encodes.decodeHex(user.getSalt());
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
					new ShiroUser(user.getId(), user.getLoginName(), user.getName(), user.getFlag(), roles),
					user.getPassword(), ByteSource.Util.bytes(salt), getName());
			this.setSession("currentUser", user);
			return authcInfo;
		} else {
			return null;
		}
	}
	
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher()
	{
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
		matcher.setHashIterations(1024);

		setCredentialsMatcher(matcher);
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	@SuppressWarnings("serial")
	public static class ShiroUser implements Serializable {
		private Integer id;
		private String loginName;
		private String name;
		private Integer flag;
		private List<String> roles;

		public ShiroUser(Integer id, String loginName, String name, Integer flag, List<String> roles) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
			this.flag = flag;
			this.roles = roles;
		}

		public String getLoginName() {
			return loginName;
		}

		public Integer getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 查询用户标识
		 * 
		 * @return
		 */
		public Integer getFlag() {
			return flag;
		}

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null)
					return false;
			} else if (!loginName.equals(other.loginName))
				return false;
			return true;
		}
	}
}

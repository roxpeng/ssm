package com.rm.test;

import com.rm.rox.utils.Digests;
import com.rm.rox.utils.Encodes;

public class AddUser {

	/**
	 * 生成加盐密码和盐
	 * @param args
	 */
	public static void main(String[] args) {
		String password = "888888";
		byte[] salt = Digests.generateSalt(8);
		System.out.println(Encodes.encodeHex(salt));
		
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		System.out.println(Encodes.encodeHex(hashPassword));
	}


}

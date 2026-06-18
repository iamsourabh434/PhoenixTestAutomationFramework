package com.api.utils;

import com.api.constant.Roles;

public class AuthTokenDemoRunner {

	public static void main(String[] args) {
		
		for(int i=0;i<=70;i++) {
			String token=AuthTokenProvider.getToken(Roles.FD);
			System.out.println(token);
		}
		

	}

}

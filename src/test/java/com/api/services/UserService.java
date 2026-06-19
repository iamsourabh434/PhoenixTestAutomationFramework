package com.api.services;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	public Response userDetails(Roles role) {
		Response response =given()
		.spec(SpecUtil.requestSpecWithAuth(role))
	.when()
		.get(USERDETAILS_ENDPOINT);
		return response;
	}
}

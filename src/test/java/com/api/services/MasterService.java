package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT= "/master";
	
	public Response master(Roles role) {
		return given()
		.spec(SpecUtil.requestSpecWithAuth(role))
		.when()
		.post(MASTER_ENDPOINT);
	}

}

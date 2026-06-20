package com.api.services;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT="/userdetails";
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	public Response userDetails(Roles role) {
		LOGGER.info("making request to {} for the role {}",USERDETAILS_ENDPOINT,role);

		Response response =given()
		.spec(SpecUtil.requestSpecWithAuth(role))
	.when()
		.get(USERDETAILS_ENDPOINT);
		return response;
	}
}

package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.filters.SensitiveDataFilter;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthServices {
	
	private static final String LOGIN_ENDPOINT="/login";
	private static final Logger LOGGER = LogManager.getLogger(AuthServices.class);
	
	@Step("Perform login with the User Credentials")
	public Response login(Object userCredentials) {
		LOGGER.info("making login reqest for the payload {}",((UserBean)userCredentials).getUsername());
		Response response = given()
		.filter(new SensitiveDataFilter())		
		.spec(SpecUtil.requestSpec(userCredentials))
	    .when()
		.post(LOGIN_ENDPOINT);
		return response;

	}
}


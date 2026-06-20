package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT= "/master";
	
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);

	
	public Response master(Roles role) {
		LOGGER.info("making request to {} for the role {}",MASTER_ENDPOINT,role);
		return given()
		.spec(SpecUtil.requestSpecWithAuth(role))
		.when()
		.post(MASTER_ENDPOINT);
	}
}

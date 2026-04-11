package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.header("Authorization", AuthTokenProvider.getToken(Roles.FD))
		.log().method()
		.log().uri()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.time(lessThan(1500L))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		//.body("data.key", contains("pending_for_delivery","pending_fst_assignment","created_today"))
		.body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CountAPISchema.json"));
			
	}
	
	public void countAPIRequest_MissingAuthToken() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.log().method()
		.log().uri()
		.log().headers()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);

		
	}

}

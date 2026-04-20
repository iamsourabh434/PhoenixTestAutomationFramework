package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	@Test(description="verify if Count api is giving correct response",groups = {"api","regression","smoke"})
	public void verifyCountAPIResponse() {
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message",equalTo("Success"))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		//.body("data.key", contains("pending_for_delivery","pending_fst_assignment","created_today"))
		.body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CountAPISchema.json"));
			
	}
	@Test(description="verify if count API is giving correct status code for invalid token",groups = {"api","negative","regression","smoke"})
	public void countAPIRequest_MissingAuthToken() {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_text(401));
 
	}

}

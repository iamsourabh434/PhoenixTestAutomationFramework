package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test(description="verify if Master API is giving correct response",groups = {"api","regression","smoke"})
	
	public void masterAPITest() {
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when()
		.post("master")
		.then()
		.log().all()
		.spec(SpecUtil.responseSpec_ok())
		.body("message",equalTo("Success"))
		.body("data",notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))
		.body("data.mst_oem.size()", equalTo(2))
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/MasterAPITestFD.json"));
		
		
	}
	@Test(description="verify if Master API is giving correct status code for invalid token",groups = {"api","negative","regression","smoke"})
	public void invalidTokenMasterAPI() {
		given()

		.spec(SpecUtil.requestSpec())
		.log().all()
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_text(401));
	}

}

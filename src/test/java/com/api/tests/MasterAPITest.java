package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	
	public void masterAPITest() {
		
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(Roles.FD))
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1200L))
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
	@Test
	public void invalidTokenMasterAPI() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.header("Authorization","")
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(401);
	}

}

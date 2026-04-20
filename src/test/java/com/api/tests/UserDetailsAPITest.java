package com.api.tests;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Roles.*;
import com.api.utils.AuthTokenProvider;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	@Test(description="verify if user details API response are shown correctly",groups = {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(FD));
		given()
			.spec(SpecUtil.requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
			
	}
	

}

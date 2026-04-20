package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class loginApiTest {
	
	private UserCredentials userCredentials;
	
	@BeforeMethod(description="create payload for the login API")
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
	}
	
	
	@Test(description ="Verify login API is working for user FD", groups= {"api","regression","smoke"})
	public void loginTest() throws IOException {
		
		
		given()
			.spec(SpecUtil.requestSpec(userCredentials))
		.when()
			.post("login")
		.then()
			.spec(SpecUtil.responseSpec_ok())
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));
			
		
	}

}

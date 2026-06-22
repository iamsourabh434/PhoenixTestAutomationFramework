package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthServices;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListner.class)

public class loginApiTest {
	
	//private UserCredentials userCredentials;
	private UserBean userCredentials;
	private AuthServices authServices;
	
	@BeforeMethod(description="create payload for the login API")
	public void setup() {
		userCredentials = new UserBean("iamfd", "password");
		authServices= new AuthServices();
	}
	
	
	@Test(description ="Verify login API is working for user FD", groups= {"api","regression","smoke"})
	public void loginTest() throws IOException {
		
		
		authServices.login(userCredentials)
		.then()
			.spec(SpecUtil.responseSpec_ok())
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));
			
		
	}

}

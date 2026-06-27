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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListner.class)
@Epic("User Management")
@Feature("Authentication")

public class loginApiTest {
	
	//private UserCredentials userCredentials;
	private UserBean userCredentials;
	private AuthServices authServices;
	
	@BeforeMethod(description="create payload for the login API")
	public void setup() {
		userCredentials = new UserBean("iamfd", "password");
		authServices= new AuthServices();
	}
	
	@Story("Verify user should be able to login into the system")
	@Description("verify if FD user is able to login")
	@Severity(SeverityLevel.BLOCKER)
	
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

package com.api.tests;

import java.io.IOException;
import static com.api.constant.Roles.FD;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListner.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsAPITest {
	
	private UserService userService;
	
	@BeforeMethod(description="Setting up the userService intance")
	public void setup() {
		userService = new UserService();
	}
	@Story("User details should be shown")
	@Description("Verify if the the user details API response shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(description="verify if user details API response are shown correctly",groups = {"api","regression","smoke"})
	public void userDetailsAPITest() throws IOException {
		
		
		//Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(FD));
		userService.userDetails(FD)
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
			
	}
	

}

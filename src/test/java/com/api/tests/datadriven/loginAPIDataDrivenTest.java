package com.api.tests.datadriven;

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
public class loginAPIDataDrivenTest {
	
	private AuthServices authServices;
	
	@BeforeMethod(description = "Intilizing the Auth service")
	public void setup() {
		authServices = new AuthServices();
	}

	@Test(description = "Verify login API is working for user FD", groups = { "api", "regression",
			"dataDriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, 
			dataProvider = "LoginAPIDataProvider" // name
					// of
					// DP
					// from
					// DataProviderUtils.class																														
	)

	public void loginTest(UserBean userBean) {

		authServices.login(userBean)
		
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator
		.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));

	}

}

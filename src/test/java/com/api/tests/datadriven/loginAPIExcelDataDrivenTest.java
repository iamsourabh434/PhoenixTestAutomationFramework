package com.api.tests.datadriven;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthServices;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class loginAPIExcelDataDrivenTest {
	
	private AuthServices authServices;
	
	@BeforeMethod(description ="setting up the AuthService reference")
	public void setup() {
		authServices = new AuthServices();
	}

	@Test(description = "Verify login API is working for user FD", groups = { "api", "regression",
			"dataDriven" }, 
			dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIExcelDataProvider")

	public void loginTest(UserBean userBean) {

		
		authServices.login(userBean)
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));

	}

}

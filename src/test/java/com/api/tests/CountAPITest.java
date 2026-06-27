package com.api.tests;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListner.class)
@Epic("Job Management")
@Feature("Job Count")
public class CountAPITest {
	
	private DashboardService dashboardService;
	
	@BeforeMethod(description = "setting DashBoard Service instance")
	public void setup() {
		dashboardService = new DashboardService();
	}
	@Story("Job count details should be shown")
	@Description("Verify if the Job count details API response shown correctly ")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(description="verify if Count api is giving correct response",groups = {"api","regression","smoke"})
	public void verifyCountAPIResponse() {
		
		dashboardService.count(FD)
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
		dashboardService.count(FD)
		.then()
		.spec(SpecUtil.responseSpec_text(401));
 
	}

}

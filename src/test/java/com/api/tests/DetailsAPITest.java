package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListner.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeMethod(description=" instantiating dashboard service and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created today");
	}
	
	@Story("Job details should be shown for FD")
	@Description("Verify if the the Job details API response shown correctly for FD user")
	@Severity(SeverityLevel.CRITICAL)
	
	@Test(description ="Verify detail api is working properly", groups= {"api","regression","smoke"})
	public void detailAPITest() {
		dashboardService.detail(Roles.FD, detailPayload)
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", Matchers.equalTo("Success"));
	}
	

}

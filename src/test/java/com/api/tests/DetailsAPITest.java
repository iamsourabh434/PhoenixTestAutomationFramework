package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeMethod(description=" instantiating dashboard service and creating detail payload")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayload = new Detail("created today");
	}
	@Test(description ="Verify detail api is working properly", groups= {"api","regression","smoke"})
	public void detailAPITest() {
		dashboardService.detail(Roles.FD, detailPayload)
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", Matchers.equalTo("Success"));
	}
	

}

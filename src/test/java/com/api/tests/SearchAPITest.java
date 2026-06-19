package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.Search;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

public class SearchAPITest {
	
	private JobService jobService;
	private Search searchPayload;
	private static final String JOB_NUMBER="JOB_322383";
	
	@BeforeMethod(description = "instantiating dashboard service and creating detail payload")
	public void setup() {
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	@Test(description ="Verify if search api is working properly", groups= {"api","e2e","smoke"})
	public void searchAPITest() {
		jobService.search(Roles.FD, searchPayload)
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.body("message", Matchers.equalTo("Success"));
	}

}

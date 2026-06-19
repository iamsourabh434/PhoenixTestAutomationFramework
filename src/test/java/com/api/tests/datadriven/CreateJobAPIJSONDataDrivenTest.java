package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIJSONDataDrivenTest {
	
	private JobService jobService;
	
	@BeforeMethod(description="Instantiating the JobService ")
	public void setup() {
	
			jobService=new JobService();
	}
	
	
	@Test(description="verify if Create API is able to create inwarranty jobs",groups = {"api","regression","dataDriven","faker"},
			dataProviderClass= com.dataproviders.DataProviderUtils.class,
			dataProvider ="CreateJobAPIJsonDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload){
		
		
		jobService.createJob(Roles.FD, createJobPayload)
		.then()
		
		.log().all()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		}

}

package com.api.tests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {
	
	private CreateJobPayload createJobPayload;
	
	private final static String COUNTRY = "India";
	
	@BeforeMethod(description="Creating createJob api payload")
	public void setup() {
	
	// created the createJobpayload Object
		createJobPayload= FakerDataGenerator.generateFakeCreateJobData();
	}
	
	@Test(description="verify if Create API is able to create inwarranty jobs",groups = {"api","regression","smoke"})
	public void createJobAPITest(){
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD ,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		//.statusCode(200)
		.spec(SpecUtil.responseSpec_ok())
		.log().all()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		}

}

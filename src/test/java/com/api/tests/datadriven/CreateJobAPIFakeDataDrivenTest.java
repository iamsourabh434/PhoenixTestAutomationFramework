package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIFakeDataDrivenTest {
	
	
	
	
	@Test(description="verify if Create API is able to create inwarranty jobs",groups = {"api","regression","dataDriven"},
			dataProviderClass= com.dataproviders.DataProviderUtils.class,
			dataProvider ="createJobAPIFakerDataProvider"
			)
	public void createJobAPITest(CreateJobPayload createJobPayload){
		
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD ,createJobPayload))
		.when()
		.post("/job/create")
		.then()
		
		.log().all()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		}

}

package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Roles;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.services.JobService;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIDataDrivenTest {
	
	
	private JobService jobService;
	
	@BeforeMethod(description="Instantiating the JobService ")
	public void setup() {
	
			jobService=new JobService();
	}
	
	
	@Test(description="verify if Create API is able to create inwarranty jobs",groups = {"api","regression","dataDriven","csv"},
			dataProviderClass= com.dataproviders.DataProviderUtils.class,
			dataProvider ="CreateJobAPIDataProvider"
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

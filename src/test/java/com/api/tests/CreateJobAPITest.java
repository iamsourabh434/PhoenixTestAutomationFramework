package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest(){
		
		// created the createJobpayload Object
		Customer customer = new Customer("sourabh", "kurhade", "7740778482", "", "sourabhskn23@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("701", "Avito", "BakerSt", "pizzahut", "pune", "411014", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "19239644232628", "19239644232628", "19239644232628", "2025-04-06T18:30:00.000Z", "1", "1");
		Problems problems = new Problems(1, "Over Heating");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload creaetJobPayload = new CreateJobPayload(1, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD ,creaetJobPayload))
		.when()
		.post("/job/create")
		.then()
		.statusCode(200)
		.log().all()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		}

}

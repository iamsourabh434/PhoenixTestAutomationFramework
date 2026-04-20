package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest(){
		
		// created the createJobpayload Object
		Customer customer = new Customer("sourabh", "kurhade", "7740778482", "", "sourabhskn23@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("701", "Avito", "BakerSt", "pizzahut", "pune", "411014", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(5), "19939644254628", "19939644254628", "19939644254628", DateTimeUtil.getTimeWithDaysAgo(5), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.OVERHEATING.getCode(), "Over Heating");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload creaetJobPayload = new CreateJobPayload(ServiceLocation.Service_Location_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
		
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

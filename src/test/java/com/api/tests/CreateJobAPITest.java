package com.api.tests;

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
import io.restassured.specification.ResponseSpecification;

public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;
	private JobService jobService;
	
	
	@BeforeMethod(description="Creating createJob api payload and setting the JobService instance")
	public void setup() {
	
	// created the createJobpayload Object
			Customer customer = new Customer("so10Ten", "Bond", "7740778470", "", "stem10@gmail.com", "");
			CustomerAddress customerAddress = new CustomerAddress("711", "Avito", "BakerSt", "pizzahut", "pune", "411014", "India", "MH");
			CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(5), "81939644257828",
					"81939644257828", "81939644257828", DateTimeUtil.getTimeWithDaysAgo(5), 
					Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
			Problems problems = new Problems(Problem.OVERHEATING.getCode(), "Over Heating");
			List<Problems> problemList = new ArrayList<Problems>();
			problemList.add(problems);
			
			createJobPayload = new CreateJobPayload(ServiceLocation.Service_Location_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
			jobService=new JobService();
	}
	
	@Test(description="verify if Create API is able to create inwarranty jobs",groups = {"api","regression","smoke"})
	public void createJobAPITest(){
		
		jobService.createJob(Roles.FD, createJobPayload)
		.then()
		.statusCode(200)
		.log()
		.all()
		.spec(SpecUtil.responseSpec_ok())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
		
		}

}

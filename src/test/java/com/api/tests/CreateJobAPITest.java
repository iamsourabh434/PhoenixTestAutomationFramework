package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest(){
		
		// created the createJobpayload Object
		Customer customer = new Customer("sourabh", "kurhade", "7740778482", "", "sourabhskn23@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("701", "Lavia", "BakerSt", "pizzahut", "pune", "411014", "India", "MH");
		CustomerProduct customerProduct = new CustomerProduct("2025-04-06T18:30:00.000Z", "12239694232628", "12239694232628", "12239694232628", "2025-04-06T18:30:00.000Z", "1", "1");
		Problems problems = new Problems(1, "Over Heating");
		Problems[] problemArray = new Problems[1];
		problemArray[0]=problems;
		CreateJobPayload creaetJobPayload = new CreateJobPayload(1, 2, 1, 1, customer, customerAddress, customerProduct, problemArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Roles.FD ,creaetJobPayload))
		.when()
		.post("/job/create")
		.then()
		.statusCode(200)
		.log().all();
	}

}

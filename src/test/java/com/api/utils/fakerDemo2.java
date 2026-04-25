package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class fakerDemo2 {
	
	private final static String COUNTRY = "India";

	public static void main(String[] args) {
		// create job api request payload
		
		Faker faker = new Faker(new Locale("en-IND"));
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("99########");
		String altMobileNumber = faker.numerify("84########");
		String customerEmailAddress= faker.internet().emailAddress();
		String altCustomerEmailAddress= faker.internet().emailAddress();
				
		
		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, customerEmailAddress, altCustomerEmailAddress);
		System.out.println(customer);
		
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landMark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#####");
		String state = faker.address().state();
		
		
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landMark, area,pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		
		String dop= DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("##############");
		String popurl = faker.internet().url();
		
		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, 1, 1);
		System.out.println(customerProduct);
		
		
		// generate random number from 1-27
		
		Random random = new Random();
		int problemID = random.nextInt((26)+1);
		String remark = faker.lorem().sentence(5);
		
		Problems problems = new Problems(problemID, remark);
		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(createJobPayload);
		
		
		
		
		
		
		
	
		
	}
	

}

package com.database.model;

public class DemoRunner {

	public static void main(String[] args) {
		
		CustomerDBModel customer = new CustomerDBModel("sourabh", "LastNameK", "9922708445", "", "taomoa@test.com", "");
		
		
		customer.setEmail_id_alt("knoutock@test.com");
		System.out.println(customer);

	}

}

package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class fakerDemo {

	public static void main(String[] args) {
		
		Faker faker = new Faker(new Locale("en-IND"));
		
		String firstName = faker.name().firstName();//imp
		String lastName = faker.name().lastName();
		String bloodGroup = faker.name().bloodGroup();
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(bloodGroup);
		
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);//imp
		System.out.println(faker.address().streetAddress());//imp
		System.out.println(faker.address().streetName());
		System.out.println(faker.address().cityName());
		
		System.out.println(faker.number().digit());
		System.out.println(faker.number().digits(4));
		System.out.println(faker.numerify("9970######")); // imp
		
		System.out.println(faker.internet().emailAddress()); // imp
		System.out.println(faker.phoneNumber().cellPhone());
		

	}

}

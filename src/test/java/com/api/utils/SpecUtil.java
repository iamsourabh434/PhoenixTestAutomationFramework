package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	public static RequestSpecification requestSpec() {
		// work for GET and DEL method
		RequestSpecification request = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.log(LogDetail.HEADERS)
		.build();
		return request;
				
				
	}
	
	public static RequestSpecification requestSpec(Object payload) {
		// work for POST PUT PATCH
		RequestSpecification requestSpeci = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.BODY)
		.log(LogDetail.HEADERS)
		.build();
		return requestSpeci;
						
	}
	
	public static RequestSpecification requestSpecWithAuth(Roles role) {
		RequestSpecification requestSpeci = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.log(LogDetail.HEADERS)
				.build();
				return requestSpeci;
	}
	public static RequestSpecification requestSpecWithAuth(Roles role ,Object payload) {
		RequestSpecification requestSpeci = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.log(LogDetail.URI)
				.log(LogDetail.METHOD)
				.log(LogDetail.BODY)
				.log(LogDetail.HEADERS)
				.build();
				return requestSpeci;
	}
	
	public static ResponseSpecification responseSpec_ok() {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(2000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	public static ResponseSpecification responseSpec_text(int statusCode) {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	

}

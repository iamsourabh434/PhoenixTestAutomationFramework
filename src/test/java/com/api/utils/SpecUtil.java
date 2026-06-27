package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Roles;
import com.api.filters.SensitiveDataFilter;
import com.api.request.model.UserCredentials;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	
	@Step("Setting up the BASE URI and CONTENT TYPE AS Application/json and attaching the SensitiveDataFilter")
	public static RequestSpecification requestSpec() {
		// work for GET and DEL method
		RequestSpecification request = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addFilter(new SensitiveDataFilter())
		.addFilter(new AllureRestAssured())
		//.log(LogDetail.URI)
		//.log(LogDetail.METHOD)
		//.log(LogDetail.BODY)
		//.log(LogDetail.HEADERS)
		.build();
		return request;
				
				
	}
	@Step("Setting up the BASE URI and CONTENT TYPE AS Application/json and attaching the SensitiveDataFilter")
	public static RequestSpecification requestSpec(Object payload) {
		// work for POST PUT PATCH
		RequestSpecification requestSpeci = new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.addFilter(new SensitiveDataFilter())
		.addFilter(new AllureRestAssured())
		//.log(LogDetail.URI)
		//.log(LogDetail.METHOD)
		//.log(LogDetail.BODY)
		//.log(LogDetail.HEADERS)
		.build();
		return requestSpeci;
						
	}
	@Step("Setting up the BASE URI and CONTENT TYPE AS Application/json and attaching the SensitiveDataFilter for a role")
	public static RequestSpecification requestSpecWithAuth(Roles role) {
		RequestSpecification requestSpeci = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				//.log(LogDetail.URI)
				//.log(LogDetail.METHOD)
				//.log(LogDetail.BODY)
				//.log(LogDetail.HEADERS)
				.build();
				return requestSpeci;
	}
	@Step("Setting up the BASE URI and CONTENT TYPE AS Application/json and attaching the SensitiveDataFilter for a role and attaching payload")
	public static RequestSpecification requestSpecWithAuth(Roles role ,Object payload) {
		RequestSpecification requestSpeci = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				//.log(LogDetail.URI)
				//.log(LogDetail.METHOD)
				//.log(LogDetail.BODY)
				//.log(LogDetail.HEADERS)
				.build();
				return requestSpeci;
	}
	@Step("Expecting the response have content type as Application/json,status code 200 and time less than 200")
	public static ResponseSpecification responseSpec_ok() {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(2000L))	
		//.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	@Step("Expecting the response have content type as Application/json,time less than 200 and status code")
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2000L))
		//.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	@Step("Content type should be Text and response time less than 2000ms and Status code")
	public static ResponseSpecification responseSpec_text(int statusCode) {
		ResponseSpecification responseSpeci =new ResponseSpecBuilder()
		
		.expectStatusCode(statusCode)
		.expectResponseTime(Matchers.lessThan(2000L))
		//.log(LogDetail.ALL)
		.build();
		
		return responseSpeci;
	}
	
}

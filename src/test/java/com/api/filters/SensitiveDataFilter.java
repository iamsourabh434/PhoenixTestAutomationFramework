package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec,FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		System.out.println("****** Hello Filter ******");
		
		redactPayload(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);// making the request --> response
		
		System.out.println("***I got Response in the Filter ******");
		redactResponseBody(response);
		return response;
	}
	
	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", 
				"\"token\": \"[REDACTED]\"");
		LOGGER.info("Response Body : {}",responseBody);
		
	}

	// method to Redact / hide the password from the request Payload
	public void redactPayload(FilterableRequestSpecification requestSpec) {
		String requestPayload =(requestSpec.getBody().toString()); // print the request body in string format
		// journey to hide payload
		requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", 
				"\"password\": \"[REDACTED]\"");
		LOGGER.info("Request Payload : {}",requestPayload);
	}

}

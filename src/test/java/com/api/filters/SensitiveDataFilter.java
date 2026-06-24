package com.api.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	
	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec,FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("*********** Request Details *************");
		
		LOGGER.info("BASE URi: {}",requestSpec.getURI());
		LOGGER.info("HTTP METHOD: {}", requestSpec.getMethod());
		//LOGGER.info("Request Header:\n {}",requestSpec.getHeaders());
		
		redactHeader(requestSpec);
		redactPayload(requestSpec);
		
		Response response = ctx.next(requestSpec, responseSpec);// making the request --> response
		LOGGER.info("*********** Response Details *************");
		LOGGER.info("STATUS : {}",response.getStatusLine());
		LOGGER.info("Response time: {} ms",response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("Response Header:\n {}",response.getHeaders());
		
		
		redactResponseBody(response);
		return response;
	}
	
	private void redactHeader(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();
		for(Header h : headerList) {
			if(h.getName().equalsIgnoreCase("Authorization")) {
				LOGGER.info("HEADER {} : {}",h.getName(),"\"[REDACTED]\"");
			}else {
				LOGGER.info("HEADER {} : {}",h.getName(), h.getValue());
			}
		}
	}

	private void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString();
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", 
				"\"token\": \"[REDACTED]\"");
		LOGGER.info("Response Body : \n {}",responseBody);
		
	}

	// method to Redact / hide the password from the request Payload
	public void redactPayload(FilterableRequestSpecification requestSpec) {
		if(requestSpec.getBody()!=null) {
		String requestPayload =(requestSpec.getBody().toString()); // print the request body in string format
		
		// journey to hide payload
		requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", 
				"\"password\": \"[REDACTED]\"");
		LOGGER.info("Request Payload :\n {}",requestPayload);
	}

}
}

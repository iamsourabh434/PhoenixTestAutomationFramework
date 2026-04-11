package com.api.tests;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Roles.*;
import com.api.utils.AuthTokenProvider;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken(FD));
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.header(authHeader)
			.contentType(ContentType.JSON)
			.log().method()
			.log().headers()
			.log().uri()
		.when()
			.get("userdetails")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1500L))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
			
	}
	

}

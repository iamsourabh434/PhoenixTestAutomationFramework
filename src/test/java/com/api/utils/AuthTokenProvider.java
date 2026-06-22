package com.api.utils;

import static com.api.constant.Roles.ENG;
import static com.api.constant.Roles.FD;
import static com.api.constant.Roles.QC;
import static com.api.constant.Roles.SUP;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private static Map<Roles,String> tokenCache = new ConcurrentHashMap<Roles,String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);
	
	 private AuthTokenProvider() {
		 
	 }

	public static String getToken(Roles role) {
		
		LOGGER.info("Checking if the token for {} is present in the cache {}", role);
		
		if(tokenCache.containsKey(role)) {
			LOGGER.info("Token found for {}",role);
			return tokenCache.get(role);
		}
		LOGGER.info("Token not found for the role {}",role);
		
		UserCredentials userCredentials=null;
		if(role==FD){
			userCredentials = new UserCredentials("iamfd","password");
		}
		else if(role==SUP) {
			userCredentials = new UserCredentials("iamsup","password");
		}
		else if(role==ENG) {
			userCredentials = new UserCredentials("iameng","password");
		}
		else if(role==QC) {
			userCredentials = new UserCredentials("iamqc","password");
		}
		
		String token= given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.body(userCredentials)
		.when()
		.post("login")
		.then()
		.log().ifValidationFails()
		.statusCode(200)
		.body("message",equalTo("Success"))
		.extract()
		.body()
		.jsonPath().get("data.token");
		
		LOGGER.info("Token cached for the future");

	tokenCache.put(role, token);
	return token;
	}
}

package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.ANY;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	@Test(description = "Verify login API with valid credentials")
	public void loginAPITest() {
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		given()
			.baseUri("http://64.227.160.186:9000/v1")
			.and()
			.contentType(JSON)
			.and()
			.accept(ANY)
			.and()
			.body(userCredentials)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("/login")
		.then()
			.log().all()			
			.statusCode(200)
			.time(lessThan(1500L))
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
	}
}

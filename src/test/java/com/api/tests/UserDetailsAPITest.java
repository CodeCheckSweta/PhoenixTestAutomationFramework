package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test(description = "Verify user details API with valid credentials")
	public void userDetailsAPITest() throws IOException {

		given().spec(SpecUtil.requestSpecWithAuth(FD)).when().get("userdetails").then().spec(SpecUtil.responseSpec_OK()).and().body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}

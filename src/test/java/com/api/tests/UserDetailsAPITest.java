package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	@Test(description = "Verify if the user details API response is shown correctly", groups= {"api", "smoke", "regression"})
	public void userDetailsAPITest() throws IOException {

		given().spec(requestSpecWithAuth(FD)).when().get("userdetails").then().spec(responseSpec_OK()).and().body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}

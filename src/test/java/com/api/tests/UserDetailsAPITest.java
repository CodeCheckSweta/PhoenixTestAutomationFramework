package com.api.tests;

import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static com.api.constant.Role.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test(description = "Verify user details API with valid credentials")
	public void userDetailsAPITest() throws IOException {
		Header authHeader = new Header("Authorization", getToken(FD));

		given().baseUri(getProperty("BASE_URI")).and().header(authHeader).and().accept(ContentType.JSON).log().uri()
				.log().method().log().body().log().headers().when().get("userdetails").then().log().all()
				.statusCode(200).time(lessThan(2000L)).and().body("message", equalTo("Success")).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

	}
}

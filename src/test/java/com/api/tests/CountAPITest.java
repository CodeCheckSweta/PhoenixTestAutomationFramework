package com.api.tests;

import static com.api.constant.Role.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	@Test
	public void verifyCountAPIResponse() {
		given().baseUri(getProperty("BASE_URI")).and().header("Authorization", getToken(FD)).log().uri().log().method()
				.log().headers().when().get("/dashboard/count").then().log().all().statusCode(200).and()
				.time(lessThan(1000L)).body("message", equalTo("Success")).body("data", notNullValue())
				.body("data.size()", equalTo(3)).body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
	}

	@Test
	public void countAPITest_missingAuthToken() {
		given().baseUri(getProperty("BASE_URI")).log().uri().log().method().log().headers().when()
				.get("/dashboard/count").then().log().all().statusCode(401).and().time(lessThan(1000L));
	}
}

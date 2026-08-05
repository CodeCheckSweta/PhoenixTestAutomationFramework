package com.api.tests;

import static com.api.constant.Role.*;
import static com.api.utils.ConfigManager.*;
import static com.api.utils.AuthTokenProvider.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	@Test
	public void masterAPITest() {
		given().baseUri(getProperty("BASE_URI")).and().header("Authorization", getToken(FD)).and().contentType("").log()
				.uri().log().method().log().headers().when().post("master").then().log().all().statusCode(200)
				.time(lessThan(2000L)).body("message", equalTo("Success")).body("data", notNullValue())
				.body("data", hasKey("mst_oem")).body("$", hasKey("data")).body("data.mst_oem.size()", greaterThan(0))
				.body("data.mst_model.size()", equalTo(3)).body("data.mst_model.id", everyItem(notNullValue()))
				.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema-FD.json"));
	}

	@Test
	public void invalidTokenMasterAPITest() {
		given().baseUri(getProperty("BASE_URI")).and().header("Authorization", "").and().contentType("").log().uri()
				.log().method().log().headers().when().post("master").then().log().all().statusCode(401).and()
				.time(lessThan(2000L));
	}
}

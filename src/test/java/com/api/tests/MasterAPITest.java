package com.api.tests;

import static com.api.constant.Role.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {
	@Test
	public void masterAPITest() {
		given().spec(SpecUtil.requestSpecWithAuth(FD)).when().post("master").then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success")).body("data", notNullValue())
				.body("data", hasKey("mst_oem")).body("$", hasKey("data")).body("data.mst_oem.size()", greaterThan(0))
				.body("data.mst_model.size()", equalTo(3)).body("data.mst_model.id", everyItem(notNullValue()))
				.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema-FD.json"));
	}

	@Test
	public void invalidTokenMasterAPITest() {
		given().spec(SpecUtil.requestSpec()).when().post("master").then().spec(SpecUtil.responseSpec_TEXT(401));
	}
}

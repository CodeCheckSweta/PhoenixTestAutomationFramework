package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPIJSONDataDrivenTest {
	
	
	@Test(description = "Verify if login API is working for user iamfd", 
			groups={"api", "regression", "smoke"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIJSONDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {
		given()
			.spec(requestSpec(userCredentials))
			.when()
			.post("/login")
			.then()
			.spec(responseSpec_OK())
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}
}

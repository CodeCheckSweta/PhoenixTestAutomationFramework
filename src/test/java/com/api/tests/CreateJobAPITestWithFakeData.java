package com.api.tests;

import static com.api.constant.Role.*;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.CreateJobPayload;
import com.api.utils.FakerDataGenerator;

public class CreateJobAPITestWithFakeData {
	
CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Creating the create jobAPI request payload")
	public void setup() {

		createJobPayload = FakerDataGenerator.generatefakeCreateJobData();
	}

	@Test(description = "Verify if the create job API is able to create Inwarranty job", groups= {"api", "smoke", "regression"})
	public void createJobAPITest() {


		given().spec(requestSpecWithAuth(FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.id", notNullValue())
				.body("data.mst_service_location_id", equalTo(1)).body("data.job_number", startsWith("JOB_"));

	}
}

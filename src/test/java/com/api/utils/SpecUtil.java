package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.*;

import com.api.constant.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	public static RequestSpecification requestSpec() {
		return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI")).setContentType(JSON).setAccept(JSON)
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
	}

	public static RequestSpecification requestSpec(Object payload) {
		return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI")).setContentType(JSON).setAccept(JSON)
				.setBody(payload).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)
				.build();
	}

	public static RequestSpecification requestSpecWithAuth(Role role) {
		return new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI")).setContentType(JSON).setAccept(JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).log(LogDetail.URI).log(LogDetail.METHOD)
				.log(LogDetail.HEADERS).log(LogDetail.BODY).build();
	}

	public static ResponseSpecification responseSpec_OK() {
		return new ResponseSpecBuilder().expectContentType(JSON).expectStatusCode(200)
				.expectResponseTime(lessThan(2000L)).log(LogDetail.STATUS).log(LogDetail.HEADERS).log(LogDetail.BODY)
				.build();
	}
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		return new ResponseSpecBuilder().expectContentType(JSON).expectStatusCode(statusCode)
				.expectResponseTime(lessThan(2000L)).log(LogDetail.STATUS).log(LogDetail.HEADERS).log(LogDetail.BODY)
				.build();
	}
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		return new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectResponseTime(lessThan(2000L)).log(LogDetail.STATUS).log(LogDetail.HEADERS).log(LogDetail.BODY)
				.build();
	}
}

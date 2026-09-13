package com.parabank.automation.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiHelper {

	private static final String BASE_URI = ConfigReader.getProperty("api.base.uri");

	public static int createAccount(int customerId, int accountType, int fromAccountId) {
		Response response = given().baseUri(BASE_URI).header("Accept", "application/json")
				.queryParam("customerId", customerId).queryParam("newAccountType", accountType)
				.queryParam("fromAccountId", fromAccountId).when().post("/createAccount").then().statusCode(200)
				.extract().response();

		return response.jsonPath().getInt("id");
	}

}

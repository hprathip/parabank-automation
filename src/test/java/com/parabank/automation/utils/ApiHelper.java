package com.parabank.automation.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.List;

public class ApiHelper {

	private static final String BASE_URI = ConfigReader.getProperty("api.base.uri");

	public static int createAccount(int customerId, int accountType, int fromAccountId) {
		Response response = given().baseUri(BASE_URI).header("Accept", "application/json")
				.queryParam("customerId", customerId).queryParam("newAccountType", accountType)
				.queryParam("fromAccountId", fromAccountId).when().post("/createAccount").then().statusCode(200)
				.extract().response();

		return response.jsonPath().getInt("id");
	}

	public static int getCustomerId(String username, String password) {
		Response response = given().baseUri(BASE_URI).header("Accept", "application/json").when()
				.get("/login/" + username + "/" + password).then().statusCode(200).extract().response();

		return response.jsonPath().getInt("id");

	}

	public static int getAccountIdForCustomer(int customerId) {
		Response response = given().baseUri(BASE_URI).header("Accept", "application/json").when()
				.get("/customers/" + customerId + "/accounts").then().statusCode(200).extract().response();
		
//		List<Integer> allAccountIds = response.jsonPath().getList("id");

		return response.jsonPath().get("[0].id");
	}

//	int customerId = ApiHelper.getCustomerId("john", "demo");
//	int newAccountId = ApiHelper.createAccount(customerId, 0, existingFromAccountId);

}

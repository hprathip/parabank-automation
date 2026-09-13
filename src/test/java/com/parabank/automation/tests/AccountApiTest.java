package com.parabank.automation.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.automation.utils.ApiHelper;

import io.restassured.response.Response;

public class AccountApiTest {

	@Test
	public void verifyGetAccountDetails() {
		baseURI = "https://parabank.parasoft.com/parabank/services/bank";

		given().header("Accept", "application/json").when().get("/accounts/12345").then().statusCode(200)
				.body("id", equalTo(12345)).body("type", equalTo("CHECKING")).body("balance", notNullValue());

	}

	@Test
	public void verifyCreateAccount() {
		baseURI = "https://parabank.parasoft.com/parabank/services/bank";

		given().header("Accept", "application/json").queryParam("customerId", 12212).queryParam("newAccountType", 0)
				.queryParam("fromAccountId", 13788).when().post("/createAccount").then().statusCode(200)
				.body("customerId", equalTo(12212)).body("type", equalTo("CHECKING")).body("balance", equalTo(0));
	}
	
	@Test
	public void verifyApiHelperCreateAccount() {
	    int newAccountId = ApiHelper.createAccount(12212, 0, 13788);
	    System.out.println("New Account ID: " + newAccountId);
	    Assert.assertTrue(newAccountId > 0, "Account ID should be a positive number");
	}

}

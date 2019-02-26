package com.qa.retriever.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

public class RetrieverRestTest {
	private String basePath = "http://retriever:8080";
	
	private String accounts = basePath + "/accounts";
	private String getAccounts = accounts + "/getAccounts";
	private String getAccountByID = accounts + "/getByAccountID/";
	private String getAccountByEmail = accounts + "/getByEmail/";
	private String getAccountByCohortID = accounts + "/getByCohortID/";
	
	private String forms = basePath + "/feedbackForm";
	private String getAllFeedbackForms = forms + "/getAllFeedbackForms";
	private String getFeedbackFormByID = forms + "/getFeedbackFormByID/";

	private String cohorts = basePath + "/cohorts";
	private String getAllCohorts = cohorts + "/getCohorts";
	private String getCohortByID = cohorts + "/getCohortByID/";
	
	@Test
    public void verifyGetAccounts() {
		given().when().get(getAccounts).then().statusCode(200);
	}
	
	@Test
	public void verifyGetAccountById() {
		given().when().get(getAccountByID + "1").then().body("accountID",equalTo(1));
	}
	
	@Test
	public void verifyGetAccountByEmail() {
		given().when().get(getAccountByEmail + "Test.Account@qa.com").then().body("email",equalTo("Test.User@qa.com"));
	}
	
	@Test
	public void verifyGetAccountsByCohortId() {
		given().when().get(getAccountByCohortID + "1").then().statusCode(200);
	}
	
	@Test
	public void verifyGetAllFeedbackForms() {
		given().when().get(getAllFeedbackForms).then().statusCode(200);
	}
	
	@Test
	public void verifyGetFeedbackFormByID() {
		given().when().get(getFeedbackFormByID + "1").then().body("feedbackID",equalTo(1));
	}
	
	@Test
	public void verifyGetAllCohorts() {
		given().when().get(getAllCohorts).then().statusCode(200);
	}
	
	@Test
	public void verifyGetCohortByID() {
		given().when().get(getCohortByID + "1").then().body("cohortID",equalTo(1));
	}
}

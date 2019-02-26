package com.qa.gateway.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;
import com.qa.cohort.persistence.domain.Cohort;
import com.qa.submit.persistence.domain.FeedbackForm;

public class GatewayRestTest {
	private static String basePath ="http://gateway:8080/gateway/";
	
	private static String createAccount = basePath + "createAccount";
	private static String createCohort = basePath + "createCohort";
	private static String createFeedback = basePath + "addFeedbackForm";
	
	private static String deleteAccount = "http://retriever:8080/accounts/deleteByEmail/";
	private static String deleteCohort = "http://retriever:8080/cohorts/deleteByCohortName/";
	private static String deleteFeedback = "http://retriever:8080/feedbackForm/deleteByAccountID/";
	
	private static String getAccounts = basePath + "getAccounts";
	private static String getAccountByID = basePath + "getAccountByAccountID/";
	private static String getAccountByEmail = basePath + "getAccountsByCohortID/";
	private static String getAccountByCohortID = basePath + "getAccountByEmail/";
	
	private static String getAllFeedbackForms = basePath + "getAllFeedbackForms";
	private static String getFeedbackFormByID = basePath + "getFeedbackFormByID/";

	private static String getAllCohorts = basePath + "getCohorts";
	private static String getCohortByID = basePath + "getCohortByCohortID/";
	
	private static String tempEmail = "Temp.User@qa.com";
	private static String cohortName = "Cohort Name";
	
	@Test
    public void verifyCreateAccount() {
		Account account = new Account(1L,true,"Temp","User",tempEmail,"password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(createAccount)
        .then().statusCode(200);
	}
	
	@Test
    public void verifyCreateCohort() {
		Cohort account = new Cohort(cohortName,"Trainer Name",1,"description");
		given()
        .contentType("application/json")
        .body(account)
        .when().post(createCohort)
        .then().statusCode(200);
	}
	
	@Test
    public void verifyAddFeedbackForm() {
    	FeedbackForm feedbackForm = new FeedbackForm(1L, 1L, 2, 8, "This week went good", "Excellent", "Test", "How is it");    	
    	given()
        .contentType("application/json")
        .body(feedbackForm)
        .when().post(createFeedback)
        .then().statusCode(200);
    }
	
	@Test
    public void verifyGetAccounts() {
		given().when().get(getAccounts).then().statusCode(200);
	}
	
	@Test
	public void verifyGetAccountById() {
		given().when().get(getAccountByID + "1").then().statusCode(200);
	}
	
	@Test
	public void verifyGetAccountByEmail() {
		given().when().get(getAccountByEmail + "Test.User@qa.com").then().statusCode(200);
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
		given().when().get(getFeedbackFormByID + "1").then().statusCode(200);
	}
	
	@Test
	public void verifyGetAllCohorts() {
		given().when().get(getAllCohorts).then().statusCode(200);
	}
	
	@Test
	public void verifyGetCohortByID() {
		given().when().get(getCohortByID + "1").then().statusCode(200);
	}
	
	@AfterClass
	public static void terminate() {
		given().when().delete(deleteAccount + tempEmail).then();
		given().when().delete(deleteCohort + cohortName).then();
    	given().when().delete(deleteFeedback + "1").then();
	}
	
}

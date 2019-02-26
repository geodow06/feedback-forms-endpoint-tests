package com.qa.gateway.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;
import com.qa.cohort.persistence.domain.Cohort;
import com.qa.submit.persistence.domain.FeedbackForm;

public class GatewayRestTest {
	private String basePath ="http://gateway:8080/gateway/";
	
	private String createAccount = basePath + "createAccount";
	private String createCohort = basePath + "createCohort";
	private String createFeedback = basePath + "addFeedbackForm";
	
	private String deleteAccount = "http://accounts:8080/accounts/deleteByEmail/";
	private String deleteCohort = "http://cohorts:8080/cohorts/deleteByCohortName/";
	private String deleteFeedback = "http://submit:8080/feedbackForm/deleteByAccountID/";
	
	private String getAccounts = basePath + "getAccounts";
	private String getAccountByID = basePath + "getAccountByAccountID/";
	private String getAccountByEmail = basePath + "getAccountsByCohortID/";
	private String getAccountByCohortID = basePath + "getAccountByEmail/";
	
	private String getAllFeedbackForms = basePath + "getAllFeedbackForms";
	private String getFeedbackFormByID = basePath + "getFeedbackFormByID/";

	private String getAllCohorts = basePath + "getCohorts";
	private String getCohortByID = basePath + "getCohortByCohortID/";
	
	private String email = "Test.User@qa.com";
	private String cohortName = "Cohort Name";
	
	@Test
    public void verifyCreateAccount() {
		Account account = new Account(1L,true,"New","User",email,"password",false);
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
	public void terminate() {
		given().when().delete(deleteAccount + email).then();
		given().when().delete(deleteCohort + cohortName).then();
    	given().when().delete(deleteFeedback + "1").then();
	}
	
}
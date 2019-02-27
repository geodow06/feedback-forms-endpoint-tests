package com.qa.retriever.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;
import com.qa.cohort.persistence.domain.Cohort;
import com.qa.submit.persistence.domain.FeedbackForm;

public class RetrieverRestTest {
	private static String basePath = "http://retriever:8080";
	
	private static String accounts = basePath + "/accounts";
	private static String getAccounts = accounts + "/getAccounts";
	private static String getAccountByID = accounts + "/getByAccountID/";
	private static String getAccountByEmail = accounts + "/getByEmail/";
	private static String getAccountByCohortID = accounts + "/getByCohortID/";
	
	private static String forms = basePath + "/feedbackForm";
	private static String getAllFeedbackForms = forms + "/getAllFeedbackForms";
	private static String getFeedbackFormByID = forms + "/getFeedbackFormByID/";

	private static String cohorts = basePath + "/cohorts";
	private static String getAllCohorts = cohorts + "/getCohorts";
	private static String getCohortByID = cohorts + "/getCohortByID/";
	
	private static String postAccount = "http://accounts:8080/accounts/createAccount";
	private static String deleteAccount = "http://retriever:8080/accounts/deleteByEmail/";
	private static String postCohort = "http://cohorts:8080/cohorts/createCohort";
	private static String deleteCohort = "http://retriever:8080/cohorts/deleteByCohortName/";
	private static String postForm = "http://submit:8080/feedbackForm/addFeedbackForm";
	private static String deleteForms = "http://retriever:8080/feedbackForm/deleteFeedbackForm/"; 
	
	private static String testEmail = "Test.User@qa.com";
	private static String cohortName = "Test Cohort";
	
	@BeforeClass
	public static void initialize() {
		Account account = new Account(1L,true,"New","User",testEmail,"password",false);
		given().contentType("application/json").body(account).when().post(postAccount).then();
		Cohort cohort = new Cohort(cohortName,"Trainer Name",1,"description");
		given().contentType("application/json").body(cohort).when().post(postCohort).then();
		FeedbackForm feedbackForm = new FeedbackForm(1L, 1L, 2, 8, "This week went good", "Excellent", "Test", "How is it");	
    	given().contentType("application/json").body(feedbackForm).when().post(postForm).then();
	}
	
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
		given().when().get(getAccountByEmail + testEmail).then().body("email",equalTo(testEmail));
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
	
	@AfterClass
	public static void terminate() {
		given().when().delete(deleteAccount + testEmail).then();
		given().when().delete(deleteCohort + cohortName).then();
		given().when().delete(deleteForms + "1").then();
	}
}

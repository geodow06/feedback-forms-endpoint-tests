package com.qa.accounts.rest;

import static com.jayway.restassured.RestAssured.given;  
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;

public class AccountRestTest {
	
	private String basePath ="http://accounts:8080/accounts";
	private String postAccount = basePath + "/createAccount";
	private String deleteAccount = basePath + "/deleteByEmail/"; 
	
	private String testEmail = "New.User@qa.com";
	private String trainerEmail = "New.Trainer@qa.com";
	private String traineeEmail = "New.Trainee@academytrainee.com";
	
	@Test
    public void verifyCreateAccount() {
		Account account = new Account(1L,true,"New","User",testEmail,"password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().statusCode(200);
	}
	
	@Test
	public void verifySetAdmin() {
		Account account = new Account(1L,false,"New","Trainer",trainerEmail,"password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().body("admin", equalTo(true));
	}
	
	@Test
	public void verifySetNotAdmin() {
		Account account = new Account(1L,true,"New","Trainee",traineeEmail,"password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().body("admin", equalTo(false));
	}
	
	@AfterClass
	public void terminate() {
		given().when().delete(deleteAccount + testEmail).then();
		given().when().delete(deleteAccount + trainerEmail).then();
		given().when().delete(deleteAccount + traineeEmail).then();
	}

}

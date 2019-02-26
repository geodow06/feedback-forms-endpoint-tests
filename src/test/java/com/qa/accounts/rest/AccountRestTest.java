package com.qa.accounts.rest;

import static com.jayway.restassured.RestAssured.given; 
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;

public class AccountRestTest {
	
	private String basePath ="http://accounts:8080/accounts";
	private String postAccount = basePath + "/createAccount"; 
	
	@Test
    public void verifyCreateAccount() {
		Account account = new Account(1L,true,"New","User","New.User@qa.com","password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().statusCode(200);
	}
	
	@Test
	public void verifySetAdmin() {
		Account account = new Account(1L,false,"New","Trainer","New.Trainer@qa.com","password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().body("admin", equalTo(true));
	}
	
	@Test
	public void verifySetNotAdmin() {
		Account account = new Account(1L,true,"New","Trainee","New.Trainee@academytrainee.com","password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount)
        .then().body("admin", equalTo(false));
	}

}

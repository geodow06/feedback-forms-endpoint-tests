package com.qa.accounts.rest;

import static com.jayway.restassured.RestAssured.given; 
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;

import com.qa.accounts.persistence.domain.Account;

public class AccountRestTest {
	
	private String base_path ="http://accounts:8080/accounts";
	private String postAccount = base_path + "/createAccount"; 
	
	@Test
    public void verifyCreateAccount() {
		Account account = new Account(1L,false,"New","User","New.User@qa.com","password",false);
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postAccount).then()
        .statusCode(200);
	}

}

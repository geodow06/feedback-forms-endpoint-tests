package com.qa.cohort.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.qa.cohort.persistence.domain.Cohort;

public class CohortRestTest {
	private String base_path ="http://cohorts:8080/cohorts";
	private String postCohort = base_path + "/createCohort"; 
	
	@Test
    public void verifyCreateAccount() {
		Cohort account = new Cohort("Cohort Name","Tainer Name",1,"description");
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postCohort).then()
        .statusCode(200);
	}
}

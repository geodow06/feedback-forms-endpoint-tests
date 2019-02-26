package com.qa.cohort.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.qa.cohort.persistence.domain.Cohort;

public class CohortRestTest {
	private String basePath ="http://cohorts:8080/cohorts";
	private String postCohort = basePath + "/createCohort"; 
	
	@Test
    public void verifyCreateCohort() {
		Cohort account = new Cohort("Cohort Name","Tainer Name",1,"description");
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postCohort).then()
        .statusCode(200);
	}
}

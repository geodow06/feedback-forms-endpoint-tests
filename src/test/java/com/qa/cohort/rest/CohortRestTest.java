package com.qa.cohort.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.cohort.persistence.domain.Cohort;

public class CohortRestTest {
	private String basePath ="http://cohorts:8080/cohorts";
	private String postCohort = basePath + "/createCohort";
	private String deleteCohort = basePath + "/deleteByCohortName/";
	
	private String cohortName = "Cohort Name";
	
	@Test
    public void verifyCreateCohort() {
		Cohort account = new Cohort(cohortName,"Trainer Name",1,"description");
		given()
        .contentType("application/json")
        .body(account)
        .when().post(postCohort).then()
        .statusCode(200);
	}
	
	@AfterClass
	public void terminate() {
		given().when().delete(deleteCohort + cohortName).then();
	}
}

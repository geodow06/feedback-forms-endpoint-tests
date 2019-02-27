package com.qa.cohort.rest;

import static com.jayway.restassured.RestAssured.given;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.cohort.persistence.domain.Cohort;

public class CohortRestTest {
	private static String basePath ="http://cohorts:8080/cohorts";
	private static String postCohort = basePath + "/createCohort";
	private static String deleteCohort = basePath + "/deleteByCohortName/";
	
	private static String cohortName = "Cohort Name";
	
	@Test
    public void verifyCreateCohort() {
		Cohort cohort = new Cohort(cohortName,"Trainer Name",1,"description");
		given()
        .contentType("application/json")
        .body(cohort)
        .when().post(postCohort).then()
        .statusCode(200);
	}
	
	@AfterClass
	public static void terminate() {
		given().when().delete(deleteCohort + cohortName).then();
	}
}

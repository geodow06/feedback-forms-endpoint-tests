package com.qa.submit.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.AfterClass;
import org.junit.Test;

import com.qa.submit.persistence.domain.FeedbackForm;

public class FeedbackFormRestTest {
    
    private String basePath ="http://submit:8080/feedbackForm";
	private String postForm = basePath + "/addFeedbackForm";
	private String deleteForms = basePath + "/deleteFeedbackForm/"; 
    
    @Test
    public void verifyAddFeedbackForm() {
    	FeedbackForm feedbackForm = new FeedbackForm(1L, 1L, 2, 8, "This week went good", "Excellent", "Test", "How is it");
    	
    	given()
        .contentType("application/json")
        .body(feedbackForm)
        .when().post(postForm).then().statusCode(200);
    }
    
    @AfterClass
	public void terminate() {
    	given().when().delete(deleteForms + "1").then();
	}
}

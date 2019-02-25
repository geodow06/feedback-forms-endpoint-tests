package com.qa.submit.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import com.qa.submit.persistence.domain.FeedbackForm;

public class FeedbackFormRestTest {
    
    private String basePath ="http://submit:8080/feedbackForm";
	private String postForm = basePath + "/addFeedbackForm"; 
    
    @Test
    public void verifyAddFeedbackForm() {
    	FeedbackForm feedbackForm = new FeedbackForm(1L, 2L, 2, 8, "This week went good", "Excellent", "Test", "How is it");
    	
    	given()
        .contentType("application/json")
        .body(feedbackForm)
        .when().post(postForm).then()
        .body("cohortID",equalTo(1))
        .body("userID",equalTo(2))
    	.body("week",equalTo(2))
    	.body("score",equalTo(8))
    	.body("question1",equalTo("This week went good"))
    	.body("question2",equalTo("Excellent"))
    	.body("question3",equalTo("Test"))
    	.body("question4",equalTo("How is it"));
    }
}

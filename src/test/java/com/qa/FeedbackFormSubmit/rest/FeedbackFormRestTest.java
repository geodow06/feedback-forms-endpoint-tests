package com.qa.FeedbackFormSubmit.rest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;

import com.qa.FeedbackFormSubmit.FeedbackFormSubmitApplicationTests;
import com.qa.FeedbackFormSubmit.persistence.domain.FeedbackForm;

public class FeedbackFormRestTest extends FeedbackFormSubmitApplicationTests{
    
    @Test
    public void pingTest() {
    	given().when().get("/getAllFeedbackForms").then().statusCode(200);
    }
    
    
    @Test
    public void verifyGetFeedbackFormByIDWhenEmpty() {
        given().when().get("/getFeedbackFormByID/100").then()
            .body("message",equalTo("Id supplied does not exist. Id: 100"));
    }
    
    @Test
    public void verifyGetFeedbackFormByIDWhenNotEmpty() {
    	given().when().get("/getFeedbackFormByID/1").then()
        .body("feedbackID",equalTo(1))
        .body("cohortID",equalTo(1))
        .body("userID",equalTo(2))
    	.body("week",equalTo(2))
    	.body("score",equalTo(8))
    	.body("question1",equalTo("This week went good"))
    	.body("question2",equalTo("Excellent"))
    	.body("question3",equalTo("Test"))
    	.body("question4",equalTo("How is it"));
    }
    
    @Test
    public void verifyGetFeedbackFormsByUserIDEmpty() {
    	given().when().get("/getFeedbackFormsByUserID/1").then()
        .body(equalTo("[]"));
    }
    
    @Test
    public void verifyGetFeedbackFormsByUserIDNotEmpty() {
    	given().when().get("/getFeedbackFormsByUserID/2").then().statusCode(200);
    }
    
    @Test
    public void verifyAddFeedbackForm() {
    	FeedbackForm feedbackForm = new FeedbackForm(1L, 2L, 2, 8, "This week went good", "Excellent", "Test", "How is it");
    	
    	given()
        .contentType("application/json")
        .body(feedbackForm)
        .when().post("/addFeedbackForm").then()
        .body("cohortID",equalTo(1))
        .body("userID",equalTo(2))
    	.body("week",equalTo(2))
    	.body("score",equalTo(8))
    	.body("question1",equalTo("This week went good"))
    	.body("question2",equalTo("Excellent"))
    	.body("question3",equalTo("Test"))
    	.body("question4",equalTo("How is it"));
    }
    
    @Test
    public void verifyGetAllFeedbackForms() {
    	given().when().get("/getFeedbackFormsByUserID/1").then().statusCode(200);
    }
}

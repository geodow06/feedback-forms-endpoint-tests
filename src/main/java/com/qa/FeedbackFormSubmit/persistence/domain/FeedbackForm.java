package com.qa.FeedbackFormSubmit.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FeedbackForm {

	@Id
	@GeneratedValue
	private Long feedbackID;
	private Long cohortID;
	private Long userID;
	private int week;
	private int score;
	private String question1;
	private String question2;
	private String question3;
	private String question4;
	
	public FeedbackForm() {
		
	}
	
	public FeedbackForm(Long cohortID, Long userID, int week, int score, String question1, String question2, String question3, String question4) {
		this.cohortID = cohortID;
		this.userID = userID;
		this.week = week;
		this.score = score;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.question4 = question4;
	}

	public Long getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Long feedbackID) {
		this.feedbackID = feedbackID;
	}

	public Long getCohortID() {
		return cohortID;
	}

	public void setCohortID(Long cohortID) {
		this.cohortID = cohortID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getQuestion4() {
		return question4;
	}

	public void setQuestion4(String question4) {
		this.question4 = question4;
	}
	
}

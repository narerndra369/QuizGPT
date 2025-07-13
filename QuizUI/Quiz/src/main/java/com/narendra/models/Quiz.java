package com.narendra.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.JsonNode;

@Document(collection="questions")
public class Quiz {
	@Id
	
	private String  id;
	
	private String gmail;
	
	private JsonNode questions;
	
	private int score;

	public Quiz(String gmail, JsonNode questions, int score) {
		super();
		this.gmail = gmail;
		this.questions = questions;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public JsonNode getQuestions() {
		return questions;
	}

	public void setQuestions(JsonNode questions) {
		this.questions = questions;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}

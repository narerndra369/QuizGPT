package com.narendra.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.narendra.models.repository.*;

@Service
public class QuizService {
	@Autowired 
	private ModelRepository repo;
	
	
	public String addQuiz(Quiz q) {
		repo.save(q);
		System.out.println("The Quiz is saved into the DataBase");
		return "/";
	}
	
}

package com.questionservice.QuestionService.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questionservice.QuestionService.Repository.QuestionRepo;
import com.questionservice.QuestionService.model.Question;
import com.questionservice.QuestionService.model.QuestionWrapper;
import com.questionservice.QuestionService.model.Response;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepo questionRepo;
	
	

	public ResponseEntity<List<Question>> getAll() {
		
//		System.out.println(questionRepo.findAll());
		try {
		return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
	}catch(Exception e) {
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	}


	public ResponseEntity<List<Question>> getQuestionbycategory(String category) {
 		
		try {			
//	    return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
			return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.BAD_REQUEST);
	}
   catch(Exception e) {
		
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	}



	public ResponseEntity<String> add(Question question) {
		
		try {
			questionRepo.save(question);
		return new ResponseEntity<>("success",HttpStatus.OK);
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		return null;
		
}


	public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
//		List<Integer> questions = questionRepo.findRandomQuestionByCategory(categoryName, numQuestions);
		
		
		 List<Question> questions = questionRepo.findByCategory(categoryName);
		    Collections.shuffle(questions); // shuffle the list randomly
		    List<Question> SubQUestions = questions.stream().limit(numQuestions).collect(Collectors.toList());
		   
		    List<Integer> questionIds = SubQUestions.stream()
       	    .map(Question::getId)
		     .collect(Collectors.toList());
		    
		    
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
		
		
		
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id:questionIds) {
			questions.add(questionRepo.findById(id).get());
		}
		
		for(Question question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
		
	}


	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right = 0;
		
		for(Response response : responses) {
			Question question = questionRepo.findById(response.getId()).get();
			if(String.valueOf(response.getResponse().trim()).equalsIgnoreCase(question.getRightAnswer().trim()))
				right++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
}

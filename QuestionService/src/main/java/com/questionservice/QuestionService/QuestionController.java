package com.questionservice.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.QuestionService.model.Question;
import com.questionservice.QuestionService.model.QuestionWrapper;
import com.questionservice.QuestionService.model.Response;
import com.questionservice.QuestionService.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
  
	@Autowired
	private QuestionService questionService;
	 
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
//		System.out.println(questionService.getAll());
		return questionService.getAll();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return 	questionService.getQuestionbycategory(category);
	}
	
	@PostMapping("add")
	
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		  
		 return questionService.add(question);	 
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
			(@RequestParam String categoryName, @RequestParam Integer numQuestions){
		 return questionService.getQuestionForQuiz(categoryName, numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds)
	{
		return questionService.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
	
}

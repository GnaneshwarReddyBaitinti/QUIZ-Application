package com.quiz.quuiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.quuiz_service.QuestionWrapper;
import com.quiz.quuiz_service.Response;


@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
  
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
			(@RequestParam String categoryName, @RequestParam Integer numQuestions);
	
	@PostMapping("question/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);

	
	@PostMapping("question/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
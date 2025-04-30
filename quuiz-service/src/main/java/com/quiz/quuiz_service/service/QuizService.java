package com.quiz.quuiz_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quuiz_service.QuestionWrapper;
import com.quiz.quuiz_service.Response;
import com.quiz.quuiz_service.Repository.QuizRepo;
import com.quiz.quuiz_service.feign.QuizInterface;
import com.quiz.quuiz_service.model.Quiz;



@Service
public class QuizService {

	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	private QuizInterface quizInterface;
	
//	@Autowired
//	private QuestionRepo questionRepo;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//		List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);
//		 List<Question> questions = questionRepo.findByCategory(category);
//		    Collections.shuffle(questions); // shuffle the list randomly
//		    List<Question> SubQUestions = questions.stream().limit(numQ).collect(Collectors.toList());
//		Quiz quiz=new Quiz();
//		
//		quiz.setTitle(title);
//		quiz.setQuestions(SubQUestions);
//		quizRepo.save(quiz);
		
		
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questions);
		quizRepo.save(quiz);
		
		return new ResponseEntity<>("Success" , HttpStatus.CREATED);
		
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		
//		Optional<Quiz> quiz=quizRepo.findById(id);
//		List<Question> questionFromDB = quiz.get().getQuestions();
//		List<QuestionWrapper> questionForUser = new ArrayList<>();
//		for(Question q : questionFromDB) {
//			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//			questionForUser.add(qw);
//		}
		  Quiz quiz= quizRepo.findById(id).get();
		  List<Integer> questionIds = quiz.getQuestionsIds();
		   ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionFromId(questionIds);
//		return new ResponseEntity<List>(questions, HttpStatus.OK);
		   return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
//		Quiz quiz = quizRepo.findById(id).get();
//		List<Question> questions=quiz.getQuestions();
//		int right=0;
//		int i=0;
//		
//		for(Response response : responses) {
//			if(response.getResponse().equals(questions.get(i).getRightAnswer())) 
//				right++;
//				
//				i++;
//		}
//			
//			return new ResponseEntity<>(right,HttpStatus.OK);		
//	}
//		 Map<Integer, String> correctAnswerMap = new HashMap<>();
//		    for (Question q : questions) {
//		        correctAnswerMap.put(q.getId(), q.getRightAnswer().trim());
//		    }
//
//		    int right = 0;
//
//		    for (Response r : responses) {
//		        // Get the correct answer for the given question ID
//		        String correctAnswer = correctAnswerMap.get(r.getId());
//
//		        if (correctAnswer != null) {
//		            // Convert both to string and compare (case-insensitive and trimmed)
//		            String userAnswer = String.valueOf(r.getResponse()).trim();
//		            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
//		                right++;
//		            }
//		        }
//		    }

		  ResponseEntity<Integer> score = quizInterface.getScore(responses);
//		    return new ResponseEntity<>(right, HttpStatus.OK);
		  return score;
		}
	
	
}

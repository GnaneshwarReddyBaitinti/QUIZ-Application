package com.questionservice.QuestionService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.questionservice.QuestionService.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);

	
//	@Query(value = "SELECT q.id FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT numQuestions", nativeQuery = true)
//	List<Integer> findRandomQuestionByCategory(String categoryName, Integer numQuestions);

//	@Query(value = "SELECT q.id FROM question q WHERE q.category = ?1")
//	List<Integer> findRandomQuestionIdByCategory(String categoryName);
//	@Query(value = "select * from question q where q.category=?1 order by rand() limit?2" , nativeQuery = true) 
//    List<Question> findRandomQuestionsByCategory(
//			String category, int numQ);
	
//	@Query(value = "SELECT * FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
//	List<Question> findRandomQuestionsByCategory(String category, int numQ);

   
	
}

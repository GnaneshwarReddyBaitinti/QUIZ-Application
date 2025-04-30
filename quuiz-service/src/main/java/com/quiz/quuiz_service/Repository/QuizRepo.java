package com.quiz.quuiz_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.quuiz_service.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
 
}

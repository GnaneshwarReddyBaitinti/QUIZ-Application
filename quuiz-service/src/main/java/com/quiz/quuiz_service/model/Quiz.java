package com.quiz.quuiz_service.model;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ElementCollection
	private List<Integer> questionsIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getQuestionsIds() {
		return questionsIds;
	}
	public void setQuestionsIds(List<Integer> questions) {
		this.questionsIds = questions;
	}
	public Quiz(Integer id, String title, List<Integer> questions) {
		super();
		this.id = id;
		this.title = title;
		this.questionsIds = questions;
	}
	public Quiz(String title, List<Integer> questions) {
		super();
		this.title = title;
		this.questionsIds = questions;
	}
	public Quiz() {
		super();
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", questions=" + questionsIds + "]";
	}
	
	
}

package com.sana.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sana.quizapp.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}

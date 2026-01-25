package com.sana.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sana.quizapp.model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category =:category ORDER BY RANDOM() LIMIT :noOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noOfQuestions);


}

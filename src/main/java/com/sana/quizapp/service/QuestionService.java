package com.sana.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sana.quizapp.model.Question;
import com.sana.quizapp.repository.QuestionRepository;


@Service
public class QuestionService {
    
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
        return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try{
        return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestion(Question question) {
        try{
       questionRepository.save(question);
       return new ResponseEntity<>("Success",HttpStatus.CREATED);}
         catch(Exception e){
          e.printStackTrace();
         }
         return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }
}

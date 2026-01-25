package com.sana.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sana.quizapp.model.Question;
import com.sana.quizapp.model.QuestionWrapper;
import com.sana.quizapp.model.Quiz;
import com.sana.quizapp.model.Response;
import com.sana.quizapp.repository.QuestionRepository;
import com.sana.quizapp.repository.QuizRepository;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, noOfQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score = 0;

        for (Response r : responses) {

            for (Question q : questions) {   

                if (q.getId().equals(r.getQuestionId())
                        && r.getSelectedOption() != null
                        && r.getSelectedOption().equals(q.getRightAnswer())) {

                    score++;
                }
            }
        }

        return new ResponseEntity<>(score, HttpStatus.OK);

    }
}

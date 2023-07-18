package com.teluskodivan.teluskodivan.service;

import com.teluskodivan.teluskodivan.model.Question;
import com.teluskodivan.teluskodivan.model.QuestionWrapper;
import com.teluskodivan.teluskodivan.model.Quiz;
import com.teluskodivan.teluskodivan.dao.QuestioDao;
import com.teluskodivan.teluskodivan.dao.QuizDao;
import com.teluskodivan.teluskodivan.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestioDao questioDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions= questioDao.findRandomQuestionByCategory(category, numQ);
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return  new ResponseEntity<>("success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Optional <Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDb= quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser= new ArrayList<>();
        for(Question q: questionFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
       Optional <Quiz> quiz = quizDao.findById(id);
       List<Question> questions =quiz.get().getQuestions();
        int right=0;
        int i =0;
       for(Response response : responses){
           if(response.getResponse().equals(questions.get(i).getRightAnswer()))
               right++;

           i++;
       }
       return new ResponseEntity<>(right,HttpStatus.OK);
    }
}

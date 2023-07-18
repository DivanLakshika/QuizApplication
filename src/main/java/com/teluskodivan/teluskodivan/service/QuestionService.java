package com.teluskodivan.teluskodivan.service;

import com.teluskodivan.teluskodivan.model.Question;
import com.teluskodivan.teluskodivan.dao.QuestioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
   QuestioDao questioDao;
    public ResponseEntity<List<Question>> getallquestions() {
        try {
            return new ResponseEntity<>(questioDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity< List<Question> >getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questioDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
     return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST) ;
    }


    public ResponseEntity< String>  addQuestion(Question question) {
        questioDao.save(question);
        return new ResponseEntity<>( "success",HttpStatus.CREATED);
    }

    public String deleteQuestion(Question question) {
        questioDao.delete(question);
        return "deleted";
    }
}

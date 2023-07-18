package com.teluskodivan.teluskodivan.controller;

import com.teluskodivan.teluskodivan.model.Question;
import com.teluskodivan.teluskodivan.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //only work with question
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestion")
    public ResponseEntity< List<Question>> getallquestions(){
        return questionService.getallquestions() ;
    }

    @GetMapping("category/{category}")
    public ResponseEntity< List<Question> >getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }


    @PostMapping("add")
    public ResponseEntity <String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete")

    public  String deleteQuestion(@RequestBody Question question){
        return questionService.deleteQuestion(question);
    }

}

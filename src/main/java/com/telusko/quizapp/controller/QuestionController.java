package com.telusko.quizapp.controller;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
//        return "Hi, these are your questions:";
        return questionService.getAllQuestions();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);

    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }
    @DeleteMapping("delete/{qid}")
    public String deleteQuestion(@PathVariable("qid") String id) {
        return questionService.deleteQuestionById(Integer.parseInt(id));
    }

    @PutMapping("updateQuestion/{qid}")
    public String updateQuestion(@PathVariable("qid") String id, @RequestBody Question question) {

        return questionService.updateQuestion(Integer.parseInt(id), question);
    }
}

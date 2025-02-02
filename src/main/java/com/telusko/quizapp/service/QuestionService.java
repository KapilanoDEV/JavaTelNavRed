package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.Questiondao;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    Questiondao questiondao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questiondao.save(question);
        return new ResponseEntity<>( "success",HttpStatus.CREATED);
    }

    public String deleteQuestionById(Integer id) {

        Optional<Question> result = questiondao.findById(id);
        questiondao.deleteById(id);

        Question question = result.get();
        String questionToBeDeleted = question.getQuestionTitle();

        return "Deleted question:\n" + questionToBeDeleted;
    }

    public String updateQuestion(Integer qid, Question question) {
        Optional<Question> result = questiondao.findById(qid);
        if(!result.isEmpty()) {
            Question question1 = result.get();
            question1.setQuestionTitle(question.getQuestionTitle());
            question1.setCategory(question.getCategory());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            question1.setDifficultyLevel(question.getDifficultyLevel());
            question1.setRightAnswer(question.getRightAnswer());
            questiondao.save(question1);
            return "updated";
        }
        else {return "Question " + qid + " does not exist!";}
    }
}

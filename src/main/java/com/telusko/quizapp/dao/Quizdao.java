package com.telusko.quizapp.dao;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface Quizdao extends JpaRepository<Quiz,Integer> {
    @Query(value = "SELECT * FROM question q WHERE q.id IN (SELECT * FROM quiz_question_list qz WHERE qz.id=:id)",nativeQuery = true)
    Optional<Question> findQuestionsById(int id);
}

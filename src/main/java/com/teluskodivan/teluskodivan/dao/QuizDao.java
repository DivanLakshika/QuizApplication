package com.teluskodivan.teluskodivan.dao;

import com.teluskodivan.teluskodivan.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}

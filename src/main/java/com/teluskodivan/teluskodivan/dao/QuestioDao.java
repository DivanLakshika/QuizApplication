package com.teluskodivan.teluskodivan.dao;

import com.teluskodivan.teluskodivan.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestioDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);


   // @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT:numQ",nativeQuery = true)
    @Query(value = "select * from question q where q.category=:category order by Rand() LIMIT :numQ", nativeQuery = true)   //in here  have problem related to random()

    List<Question> findRandomQuestionByCategory(String category, int numQ);
}

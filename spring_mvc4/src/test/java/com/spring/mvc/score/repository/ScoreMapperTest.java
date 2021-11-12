package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ScoreMapperTest {
    @Autowired
    ScoreMapper scoreMapper;

    @Test
    void insertTest() {
        Score s = new Score("마이콜", 99, 88, 33);
        scoreMapper.save(s);
    }

    @Test
    void deleteTest() {
        scoreMapper.remove(9); //원래 맛고구마였음, 사라짐
    }

    @Test
    void findAllTest() {
        List<Score> scoreList = scoreMapper.findAll();
        System.out.println("------------------------------------");
        for (Score score : scoreList) {
            System.out.println("score = " + score);
        }
    }


}
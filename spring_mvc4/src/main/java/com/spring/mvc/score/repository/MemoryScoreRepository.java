package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@Component
@Repository // component대신 (저장소 역할 하는 애들한테는 이렇게 써줌. component기능 포함되어있음)
@Log4j2
public class MemoryScoreRepository implements ScoreRepository{

    //실제 메모리 저장소
    private Map<Integer, Score> scoreMap = new HashMap<>();

    //더미데이터

    public MemoryScoreRepository() {
        scoreMap.put(1, new Score("뽀로로", 89, 92, 88));
        scoreMap.put(2, new Score("김철수", 77, 34, 41));
        scoreMap.put(3, new Score("박영희", 86, 54, 51));
    }


    //학번을 key로 하려고 map 씀.

    @Override
    public boolean save(Score score) {
//        score.calcTotal(); // 후보2, 근데 여기서 안함. solid원칙에 의해서.
        scoreMap.put(score.getStuNum(), score);
        log.info(scoreMap);
        return true;
    }

    @Override
    public List<Score> findAll() {
        List<Score> scores = new ArrayList<>();
        for (Integer stuNum : scoreMap.keySet()) {
            Score score = scoreMap.get(stuNum);
            scores.add(score); // List로 달라해서 List에 넣는중.
        }
        return scores;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public boolean remove(int stuNum) {
        scoreMap.remove(stuNum);
        return false;
    }
}

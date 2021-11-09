package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;

import java.util.List;

//성적 정보를 저장, 조회, 수정, 삭제 하는 역할
public interface ScoreRepository {

    //성적 저장
    boolean save(Score score);
    //전체 성적정보 조회
    List<Score> findAll();
    //개별 성적 조회
    Score findOne(int stuNum);
    //성적정보 삭제
    boolean remove(int stuNum);

}

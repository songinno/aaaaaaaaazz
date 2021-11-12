package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//***마이바티스
//마이바티스 쓸려면 구현클래스 하나도 없어야함.
//라이브러리 깔고, 인터페이스 하나 만들고, xml문서 만듦.(resources에 현재 인터페이스의 패키지 구조랑 똑같이 + 이름도 똑같이, 뒤에 .xml만 붙임)
@Mapper
public interface ScoreMapper {

    //성적 저장
    boolean save(Score score);
    //전체 성적정보 조회
    List<Score> findAll();
    //개별 성적 조회
    Score findOne(int stuNum);
    //성적정보 삭제
    boolean remove(int stuNum);

}

package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Grade;
import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


//@Component
@Service
@RequiredArgsConstructor
//책임: 컨트롤러와 저장소 사이의 중간 데이터 처리 담당.
//컨트롤러가 서비스한테 저장하기 전에 총점평균 구하라고 시킬것.
public class ScoreService {

    /*
    private final ScoreRepository scoreRepository;

    @Autowired // 하나밖에 없어서 안써도됨.
    public ScoreService(@Qualifier("ssr") ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
    */
    private final ScoreMapper scoreMapper; // + @RequiredArgsConstructor
    //변수 이름 일부러 전에 쓰던거랑 똑같이함. 밑에 다 바꿔야해서. 근데,[ shift + f6] 하면 싹다 바꿀 수 있음. (우클릭 리펙터링 이름변경임)

    //저장처리 중간로직
    public void register(Score score) {
        score.calcTotal();
        scoreMapper.save(score);
    }

    //전체조회 중간처리 (+null체크 -> 500번 에러 안나게. 500 에러는 항상 안나오게 하는게 좋음.)
    public List<Score> getList() {
        List<Score> scoreList = scoreMapper.findAll();
        //이름에 마킹처리
        if (scoreList != null) {
            for (Score score : scoreList) {
                //이름 빼오기
                String name = score.getName();
                //성뽑기
                String family = String.valueOf(name.charAt(0));
                //성을 제외한 이름 수
                int length = name.length() - 1;
                for (int i = 0; i < length; i++) {
                    family += "*";
                }
                score.setMarkName(family);
            }
            return scoreList;
        } else {
            return Collections.emptyList(); // 빈 리스트 리턴
        }

    }

    //삭제 중간처리
    public void remove(int stuNum) {
        scoreMapper.remove(stuNum);
    }

    //상세조회 중간처리
    public Score getScore(int stuNum) {
        Score score = scoreMapper.findOne(stuNum);
        //중간처리
        //평균뽑기
        double avg = score.getAverage();
        if (avg >= 90) {
            score.setGrade(Grade.A);
        } else if(avg >= 80) {
            score.setGrade(Grade.B);
        } else if(avg >= 70) {
            score.setGrade(Grade.C);
        } else if(avg >= 60) {
            score.setGrade(Grade.D);
        } else {
            score.setGrade(Grade.F);
        }
        return score;
    }


}

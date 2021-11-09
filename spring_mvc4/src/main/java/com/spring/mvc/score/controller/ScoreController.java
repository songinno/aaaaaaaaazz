package com.spring.mvc.score.controller;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreRepository;
import com.spring.mvc.score.service.ScoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
//@Component
public class ScoreController {

//    private List<Score> scoreList = new ArrayList<>(); // LIST가 메모리 (DB) 역할중.
    //근데 컨트롤러가 이것까지 하면 역할이 너무 커짐. -> 저장소 인터페이스 만듦
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }


    //성적 입력 화면 열어주는 요청
    @GetMapping("/score/score-list")
    public String scoreList() {
        return "/score/score-list";
    }

    //성적정보 저장 요청
    @PostMapping("/score/register")
    public String register(Score score) {
        //총점 평균까지 구하려면, 또 동명이인이 있다면,,, 이런거 생각해야함. 커맨드객체 만듦
        log.info("/score/register POST! - " + score);
//        scoreList.add(score);
//        System.out.println(scoreList);
//        score.calcTotal(); // 후보1, 여기 안됨. solid원칙에 의해서.
        //ScoreRepository 인터페이스 생성 이후
        scoreService.register(score);
        return "redirect:/score/list"; // redirect -> 다시들고와야하니깐 ::: url 적어야함.
    }

    //전체 성적정보 조회 요청
    @GetMapping("/score/list")
    public String list(Model model) {
        log.info("/score/list GET!!!");
        List<Score> scores = scoreService.getList();
        model.addAttribute("scores", scores);
        return "score/score-list"; // score폴더의 score-List.jsp
        //index.html : <li><a href="/score/list">Score App</a></li>로 변경
    }

    //성적정보 삭제 요청처리
    //삭제 요청: get으로 옴.(a태그 -> form 말고 다 get, post는 form 말고 없음)
    @GetMapping("/score/delete")
    public String delete(int stuNum) {
        log.info("/score/delete GET!!!" + stuNum);
//        html -> <a class="del-btn" href="/score/delete?stuNum=${s.stuNum}">
        scoreService.remove(stuNum);

        return "redirect:/score/list"; // 지운다음에 지워진 목록을 다시 보여줘야하니깐 redirect
    }

    //상세조회 요청처리
    @GetMapping("/score/detail")
    public String detail(int stuNum, Model model) {
        log.info("/score/detail GET!" + stuNum);
        Score score = scoreService.getScore(stuNum);
        model.addAttribute("s", score);
        return "score/detail";
    }



}

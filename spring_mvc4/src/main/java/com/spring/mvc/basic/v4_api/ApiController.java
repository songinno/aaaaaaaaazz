package com.spring.mvc.basic.v4_api;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApiController {

    private final BoardService boardService;

    @GetMapping("/api/hello")
    @ResponseBody //=> 클라이언트 측으로 순수한 데이터를 리턴한다. (더이상 jsp파일 경로x).
    public String hello() {

        return "안녕~";
    }

    @GetMapping("/api/list")
    @ResponseBody
    public List<String> list() {
        return Arrays.asList("짜장면", "볶음밥", "탕수육", "군만두"); // -> content type이 json
    }

    @GetMapping("/api/arr")
    @ResponseBody
    public String[] arr() {
        return new String[] {"멍멍이", "야옹이", "호랑이"}; // -> ["멍멍이","야옹이","호랑이"]
        //자바 배열{}로 보냈는데, 형태가 자바스크립트 배열 형태[]로 나옴. (json 땜에)
    }

    @GetMapping("/api/article")
    @ResponseBody
    public Board board() {
        Board board = new Board();
        board.setTitle("케케케");
        board.setWriter("하하하");
        board.setContent("안녕하세요");
        board.setBoardNo(5L);
        return board;
        //=> {"boardNo":5,"writer":"하하하","title":"케케케","content":"안녕하세요","viewCnt":0,"regDate":null,"regDateStr":null,"newFlag":false,"updateDate":null}
        //자바스크립트 객체 형태.
    }

    //실제 DB데이터를 클라이언트한테 쏴보기
    @GetMapping("/api/b-list")
    @ResponseBody
    public List<Board> bList() {

        return boardService.getList(new Page());
    }





}

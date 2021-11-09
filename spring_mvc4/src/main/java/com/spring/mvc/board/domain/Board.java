package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Board {
    private Long boardNo; // 게시글번호
    private String writer; //작성자
    private String title; // 글제목
    private String content; // 글내용

    private static long seq;

    public Board() {
        this.boardNo = ++seq;
    }

    public Board(String writer, String title, String content) {
        this();
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}

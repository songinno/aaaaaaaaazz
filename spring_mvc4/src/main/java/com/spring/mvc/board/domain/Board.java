package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter @ToString
public class Board {
    private Long boardNo; // 게시글번호
    private String writer; //작성자
    private String title; // 글제목
    private String content; // 글내용

    private int viewCount; //조회수
    private String regDate; // 작성일

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

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.writer = rs.getString("writer");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        //밑에 2개는 아직 나중에.
        this.viewCount = rs.getInt("view_cnt");
        this.regDate = rs.getString("reg_date");
//        this.regDate = rs.getString(6);
    }
}

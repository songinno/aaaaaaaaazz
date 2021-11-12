package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Setter @Getter @ToString
public class Board {
    private Long boardNo; // 게시글번호
    private String writer; //작성자
    private String title; // 글제목
    private String content; // 글내용
    //++
    private int viewCnt; //조회수
    private Date regDate; // 작성일 (마이바티스에서 LocalDate 이거 까다로움.)
    private String regDateStr; // 포맷팅된 날짜문자열 (여기다 담아서 jsp로 보내려고 만듦)
    // 근데 애초에 String 타입으로 만들고 했더니 원하던 바 대로 나왔음..

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
        //++
        this.viewCnt = rs.getInt("view_cnt");
        this.regDate = rs.getDate("reg_date");
//        this.regDate = rs.getString(6);
    }
}

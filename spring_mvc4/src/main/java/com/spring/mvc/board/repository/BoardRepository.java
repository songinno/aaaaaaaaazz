package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;

import java.util.List;

public interface BoardRepository {

    //게시물 목록 조회
    List<Board> getArticles();

    //게시물 상세 조회
    Board getContent(Long boardNo);

    //게시물 등록
    boolean insert(Board board);

    //게시물 삭제
    boolean delete(Long boardNo);

    //게시물 수정
    boolean update(ModBoard board);
}

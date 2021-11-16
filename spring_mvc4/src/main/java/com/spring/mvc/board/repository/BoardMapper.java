package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.common.paging.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {

    //게시물 목록 조회 (페이징 없는 버전)
//    List<Board> getArticles();

    //***페이징 추가버전
    List<Board> getArticles(Page page);

    //게시물 상세 조회
    Board getContent(Long boardNo);

    //게시물 등록
    boolean insert(Board board);

    //게시물 삭제
    boolean delete(Long boardNo);

    //게시물 수정
    boolean update(ModBoard board);

    // 총 게시물 수
    int getTotalCount(); //-> xml에 select문 생성. -> service 중간처리


}

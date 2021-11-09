package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.board.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시물 목록 중간처리
    public List<Board> getList() {
        List<Board> articles = boardRepository.getArticles();

        //역정렬 (최신 게시물이 위로 올라오게)
        List<Board> sortedList = new ArrayList<>();
        for (int i = articles.size()-1; i >= 0; i--) {
            sortedList.add(articles.get(i));
        }
        return sortedList;
    }

    //글쓰기 중간처리
    public boolean write(Board board) {
        return boardRepository.insert(board);
    }
    //상세조회 중간처리
    public Board get(Long boardNo) {
        return boardRepository.getContent(boardNo);
    }

    //수정 중간처리
    public boolean update(ModBoard board) {
        boardRepository.update(board);
        return true;
    }

    //삭제 중간처리
    public void remove(Long boardNo) {
        boardRepository.delete(boardNo);
    }
}

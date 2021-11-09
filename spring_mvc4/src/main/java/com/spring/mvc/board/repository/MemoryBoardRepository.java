package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MemoryBoardRepository implements BoardRepository{

    //메모리 저장소 map쓸거임
    private Map<Long, Board> boardMap = new HashMap<>();

    public MemoryBoardRepository() {
        boardMap.put(1L, new Board("개그맨", "아라따리또", "아라비안나이트 신비한이야기"));
        boardMap.put(2L, new Board("addvds", "zcv", "zzzz bbbb"));
        boardMap.put(3L, new Board("xzcvxv", "1fdv", "svczc vg44rr4"));
        boardMap.put(4L, new Board("qer", "bgsbvc", "aaasdvcz bnnnnn"));
        boardMap.put(5L, new Board("jhgjng", "dadacxz", "czva evccv"));
    }

    @Override
    public List<Board> getArticles() {
        List<Board> boardList = new ArrayList<>();
        for (Long key : boardMap.keySet()) {
            boardList.add(boardMap.get(key));
        }
        return boardList;
    }

    @Override
    public Board getContent(Long boardNo) {
        return boardMap.get(boardNo);
    }

    @Override
    public boolean insert(Board board) {
        boardMap.put(board.getBoardNo(), board);
        return false;
    }

    @Override
    public boolean delete(Long boardNo) {
        boardMap.remove(boardNo);
        return false;
    }

    @Override
    public boolean update(ModBoard board) {
        //1.해시맵에 저장되어 있는 게시물 객체를 꺼낸다
        Board target = boardMap.get(board.getBoardNo());
        //2. 해당 객체의 데이터들을 ModBoard의 값으로 수정
        target.setWriter(board.getWriter());
        target.setTitle(board.getTitle());
        target.setContent(board.getContent());
        return false;
    }
}

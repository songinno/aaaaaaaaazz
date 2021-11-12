package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.dto.ModBoard;
import com.spring.mvc.board.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    /*
    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(@Qualifier("sbr") BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    */

    //게시물 목록 중간처리
    public List<Board> getList() {
        List<Board> articles = boardMapper.getArticles();
        /*
        List<Board> sortedList = null;

            //역정렬 (최신 게시물이 위로 올라오게)
            sortedList = new ArrayList<>();
            for (int i = articles.size()-1; i >= 0; i--) {
                sortedList.add(articles.get(i));
            }
            return sortedList;
        */

        /*
        for (Board article : articles) {
            //++ 날짜정보를 이쁘게
            Date regDate = article.getRegDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm");
            String prettierDate = sdf.format(regDate);
            article.setRegDateStr(prettierDate);
        }
        */ //이렇게 하고 jsp에 ${b.regDateStr}

        return articles;
    }

    //글쓰기 중간처리
    public boolean write(Board board) {
        return boardMapper.insert(board);
    }
    //상세조회 중간처리
    public Board get(Long boardNo) {
        return boardMapper.getContent(boardNo);
    }

    //수정 중간처리
    public boolean update(ModBoard board) {
        boardMapper.update(board);
        return true;
    }

    //삭제 중간처리
    public void remove(Long boardNo) {
        boardMapper.delete(boardNo);
    }
}

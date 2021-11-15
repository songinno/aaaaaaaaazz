package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 이걸 붙여야 스프링 컨테이너에서 객체를 주입받을 수 있고, 등등  붙여줘야됨.
class BoardMapperTest {
    @Autowired
    BoardMapper boardMapper;

    @Test
    void bulkInsert() {
        for (int i = 1; i <= 300; i++) {
            Board board = new Board("사람" + i, "제목제목" + i, "내용내용" + i);
            boardMapper.insert(board);
        }
    }

}
package com.spring.mvc.reply.service;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.common.paging.PageMaker;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

//    //댓글 목록조회 중간처리 (21.11.19 밑에거로 수정 페이징기능 ㅊㄱ)
//    public List<Reply> getList(Long boardNo) {
//
//        return replyMapper.getList(boardNo);
//    }

    //댓글 목록 조회
    public Map<String, Object> getList(Long boardNo, Page page) {
        PageMaker maker
                = new PageMaker(page, getCount(boardNo));

        Map<String, Object> replyMap = new HashMap<>(); // list랑 maker랑 같이 보내려고 map만듦
        replyMap.put("replyList", replyMapper.getList(boardNo, page));
        replyMap.put("maker", maker);

        return replyMap;

    }

    //댓글 쓰기 중간처리
    public boolean write(Reply reply) {
        return replyMapper.save(reply);
    }

    //댓글 총 개수 조회
    public int getCount(Long boardNo) {
        return replyMapper.getCount(boardNo);
    }
    //댓글 개별 조회
    public Reply get(Long replyNo) {
        return replyMapper.read(replyNo);
    }


    //댓글 수정 중간처리
    public boolean modify(Reply reply) {
        return replyMapper.update(reply);
    }

    //댓글 삭제
    public boolean remove(Long replyNo){
        return replyMapper.delete(replyNo);
    }









}



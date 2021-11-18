package com.spring.mvc.reply.service;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyMapper replyMapper;

    //댓글 목록조회 중간처리
    private List<Reply> getList(Long boardNo) {

        return replyMapper.getList(boardNo);
    }









}



package com.spring.mvc.reply.controller;

import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
@Log4j2
public class ReplyController {

    public final ReplyService replyService;

    /*
        1. 댓글 목록 요청: /api/v1/reply - GET
        2. 댓글 쓰기 요청: /api/v1/reply - POST
        3. 댓글 수정 요청: /api/v1/reply/댓글번호 - PUT
        4. 댓글 삭제 요청: /api/v1/reply/댓글번호 - DELETE

     */
    //댓글 목록 요청 처리
    @GetMapping("")
    public Map<String, Object> list(Long boardNo, Page page) {
        log.info("/api/v1/reply GET!");
        Map<String, Object> replies = replyService.getList(boardNo, page);
        return  replies;
    }

    //댓글 등록 요청 처리
    @PostMapping("")
    public String create(@RequestBody Reply reply) {
        log.info("/api/v1/reply POST!");
        return replyService.write(reply) ? "insert - success" : "insert-fail";
        //기존에는 form 으로 정보를 받았는데, 여긴 없는데.
        //클->서 정보를 줄때도 json으로. form없어서.  => @RequestBody: 받을때
    }

    //댓글 삭제 요청 처리
    @DeleteMapping("/{rno}") // 바뀌니까 {rno} 중괄호로 이렇게 <-이걸 읽으려고 @PathVariable("rno") (uri주소에서 읽겠다)
    public String remove(@PathVariable("rno") Long replyNo) {
        return replyService.remove(replyNo) ? "delete-success" : "delete-fail";
    }

    //댓글 수정 요청 처리
    @PutMapping("/{rno}")
    public String modify(@PathVariable("rno") Long replyNo, @RequestBody Reply reply) {
        log.info("/api/v1/reply PUT! ");
        //따로 받아서 밑에 이렇게 2개로 써줘야함.
        reply.setReplyNo(replyNo);
        return replyService.modify(reply) ? "modify-success" : "modify-fail";
    }








}

package com.spring.mvc.reply.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter @Setter @ToString
public class Reply {

    private Long replyNo;
    private String replyText;
    private String replyWriter;
    private Date replyDate;
    private Long boardNo; // 원본글 번호

}

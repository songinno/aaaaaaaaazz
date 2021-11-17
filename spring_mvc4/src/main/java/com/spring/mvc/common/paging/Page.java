package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//범용적으로 쓸거라서 패키지 common에 따로 둠.
@Setter @Getter @ToString
public class Page {
    private int pageNum; //페이지번호
    private int amount; // 한 페이지당 게시물 수

    //++++21.11.17 검색기능달기
    private String type; // 검색조건  번역(alt+2) translator jojoldu꺼 )
    private String keyword; // 검색어


    //기본값 정해놓기 (처음 화면에 10개 나오게)
    public Page() {
        this.pageNum = 1;
        this.amount = 10;

        this.keyword = "";
    }

    //이상한거 안넣게 제어하기
    public void setPageNum(int pageNum) {
        if (pageNum < 0) {
            this.pageNum = 1;
            return;
        }
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        if (amount <= 0 || amount >100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }

}
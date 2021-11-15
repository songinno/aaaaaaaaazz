package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;

//범용적으로 쓸거라서 패키지 common에 따로 둠.
@Setter @Getter
public class Page {
    private intpageNum; //페이지번호
    private int amount; // 한 페이지당 게시물 수
}

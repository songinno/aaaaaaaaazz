package com.spring.mvc.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ModBoard {
    //수정할때 필요한 애들만 만듦.
    private Long boardNo; // sql에서 수정할때 이거 없으면, 전체 수정돼서 있어야함.(where에 써야함.)
    private String writer;
    private String title;
    private String content;

}

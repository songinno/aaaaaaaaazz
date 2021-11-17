package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이지 생성정보를 만들어주는 객체 (1~5/ 6~10/ 20~25 / prev, next 줄까 말까
@Getter @Setter @ToString
public class PageMaker {

    //한 화면에 배치할 페이지 수
    private static final int PAGE_COUNT = 5;

    //시작과 끝 페이지 숫자
    private int beginPage, endPage; // jsp에서 <c:-> begin이랑 end에

    //prev, next 할까 말까
    private boolean prev, next; // li에

    // 외부에서 들어오는 데이터들 -> db에서 받아올때 초기화시키면 됨(생성자로)
    private Page page;
    private int totalCount; // 총 게시물 수

    public PageMaker(Page page, int totalCount) {
        this.page = page;
        this.totalCount = totalCount;
        makePageInfo();
    }

    //페이지 정보를 만드는 메서드
    private void makePageInfo() {
        //끝 페이지 계산
        // 지금 3페이지 보고있으면, 끝은 5 -> 지금 뭐보고있나 알아야함. => private Page page;
        // 공식: 올림 [(현재 보고있는 페이지 넘버) / 한 화면에 보여질 페이지 수)] * 한 화면에 보여질 페이지수
        this.endPage = (int) Math.ceil(page.getPageNum() / (double) PAGE_COUNT) * PAGE_COUNT;

        //시작 페이지 계산
        this.beginPage = endPage - PAGE_COUNT + 1;

        /*
            총 게시물 수: 284개, 한화면당 10페이지씩 보여주고 있으면,
            구간: 1~10 구간. 게시물 100개
                11~20페이지구간, 게시물 100개 (누적200)
                21~30페이지구간, 게시물 84개 -> 30페이지 없음. 29페이지가 마지막.
                => 마지막 구간에서는 21~29로 바뀌어야함.

                *총 게시물 수에 따라 마지막 구간(endPage)이 바뀌어야한다.
                -> 마지막 구간에서는 보정이 들어갈 수도 있음.

                #보정 공식: 올림(총 게시물 수 / 한페이지당 보여줄 게시물 수)
                284 / 10 = 28.4 -> 올림 => 29
         */
        int realEnd = (int)Math.ceil(totalCount / (double) page.getAmount()) ;
        //총게시물수 몰라 -> private int totalCount;

        //보정은 마지막 페이지 구간에서만 일어남.
        if (realEnd < endPage) {
            this.endPage = realEnd;
        }

        //이전 버튼을 활성화할지의 여부 (1~5구간 빼고 다 활성화)
        this.prev = this.beginPage > 1; //1보다 크기만 하면 (6,11,16,21, ~~~)
        //다음 버튼 활성화 여부
        this.next = this.endPage < realEnd;
    }//end makeInfo()
}

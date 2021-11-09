package com.spring.mvc.score.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Score {

    // 클라이언트가 전달하는 데이터
    private String name;
    private int kor, eng, math;

    //자체 생성 데이터
    private int stuNum; //학번: 등록 순서에 따라 순차적으로 만들어줌.
    private int total; //총점
    private double average; //평균

    private static int seq;

    private Grade grade;

    //마킹된 이름
    private String markName;


    public Score() {
        System.out.println("score 객체 생성!");
        //-> score 객체가 확인 누를 때마다 생성됨.
//        this.stuNum++; //-> 그래서 이렇게 하면, 전체 학생 공유됨(static마냥) -> seq 만들고
        this.stuNum = ++seq;
    }

    //더미데이터
    public Score(String name, int kor, int eng, int math) {
        this(); // = this.stuNum = ++seq;
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        calcTotal();
    }

    //총점, 평균을 구하는 메서드
    //총점,평균 구하기에 여기 클래스가 제일 적합함(직접적으로 데이터들을 갖고 있어서)
    public void calcTotal() {
        this.total = kor + eng + math;
        this.average = Math.round((total / 3.0)*100) / 100.0 ; // 소수점 2째자리까지 반올림.
    }
    //컨트롤러도, 레퍼지토리도 안됨. -> service 만듦.
    //컨트롤러가 서비스한테 명령하고, 서비스 중간처리가 끝나고 레퍼지토리한테 명령내리게 변경할거임.




}

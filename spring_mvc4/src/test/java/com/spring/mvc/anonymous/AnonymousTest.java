package com.spring.mvc.anonymous;

import org.junit.jupiter.api.Test;

public class AnonymousTest {

    @Test
    void test1() {
        Car sonata = new Sonata();
        sonata.run();

        //***익명클래스***
//        new Car() class Ferrari implements Car {}; 에서 class~~ 를 생략해버린것. -> overriding
        Car ferrari = new Car(){
            @Override
            public void run() {
                System.out.println("페라리가 달랍니다.");
            }
        };
        ferrari.run();
    /////////////////////////////////
        Dealer pororo = new Dealer();
        pororo.getCar(sonata);
        //그런데 딜러가 페라리를 급하게 구함. 설계도 만들 시간도 없음. -> 1회용 클래스를 만듦. = "익명클래스"
        pororo.getCar(ferrari);
        //아니면 이렇게 바로 넣어도o
        pororo.getCar(new Car(){
            @Override
            public void run() {
                System.out.println("페라리가 달랍니다.");
            }
        });

        //람다 이용
        Car avante = () -> System.out.println("아반떼가 달립니다.");
        avante.run();

        pororo.getCar(() -> System.out.println("투싼이 달립니다."));

    }//end test1

    //////////////////////////////calculator/////////////////////////////////////////////////////////
    @Test
    void test2() {
        Calculator addCal = new Calculator() {

            @Override
            public double operate(int n1, int n2) {
                return n1 + n2;
            }
        };
        System.out.println(addCal.operate(10, 20));

        Calculator multiCal = new Calculator() {

            @Override
            public double operate(int n1, int n2) {
                return n1 * n2;
            }
        };
        System.out.println(multiCal.operate(10,5));

        //위에 것도 불편하다해서 이렇게 만들어버림. (추상메서드가 1개일 때만 쓸 수 있음)
        Calculator addCal2 = (n1, n2) -> {
            return n1 + n2;
        };

        //더 줄여서 (한줄일 때만-> 중괄호 제거. return 제거) => 람다
        Calculator addCal3 = (n1, n2) -> n1 + n2;

        Calculator multiCal2 = (n1, n2) -> n1 * n2;



    }//end test2


}

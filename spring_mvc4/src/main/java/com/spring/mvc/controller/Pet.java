package com.spring.mvc.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter @Getter
public class Pet {

    //파라미터들을 필드로 매칭
    private String pet;
    private int age;
    private String master;
    private String gender;
    private List<String> hobby;

    public Pet() {
        System.out.println("커맨드 객체 Pet 생성!!");
    }
}

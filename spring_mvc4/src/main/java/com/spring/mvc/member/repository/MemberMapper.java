package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //회원 가입 기능
    boolean register(Member member);

    //id, 이메일 중복체크
    int isDuplicate(String targetKeyword);
    int isDuplicate2(String targetKeyword);

    //단일회원정보 조회 기능
    Member getUser(String account);



}

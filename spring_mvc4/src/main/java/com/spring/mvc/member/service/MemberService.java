package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    //회원 가입 기능

    //id 중복체크 중간처리(1)
    public boolean isDuplicate(String checkId) {
        return memberMapper.isDuplicate(checkId) == 1;  //1이 나오면 중복.
    }

    //이메일 중복체크
    public boolean isDuplicate2(String checkEmail) {
        return memberMapper.isDuplicate2(checkEmail) == 1;  //1이 나오면 중복.
    }
    //회원가입 중간처리 (평문 비밀번호 암호화)
    public void signUp(Member member) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPw = encoder.encode(member.getPassword());
        member.setPassword(encodedPw);

        memberMapper.register(member);
    }
    ////단일회원정보 조회 기능
}

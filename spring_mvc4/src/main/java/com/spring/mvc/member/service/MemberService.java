package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.LoginFlag;
import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.member.domain.LoginFlag.*;

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
    //로그인 중간처리
    public LoginFlag login(String account, String password) {
        Member member = memberMapper.getUser(account);
        if (member != null) {
//            일단ID는 있음
            //DB패스워드
            String dbPw = member.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(password, dbPw) ? SUCCESS : NO_PW;
        } else {
            return NO_ID; //import static com.spring.mvc.member.domain.LoginFlag.*; -> 앞에 LoginFlag.  안붙여도 됨.(alt+enter로)
        }

    }
    //회원정보 조회하기
    public Member getMember(String account) {
        return memberMapper.getUser(account);
    }

}

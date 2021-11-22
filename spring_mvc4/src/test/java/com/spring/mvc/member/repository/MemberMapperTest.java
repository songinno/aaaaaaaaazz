package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberMapperTest {
    @Autowired MemberMapper memberMapper;

    @Test
    @DisplayName("비밀번호가 인코딩된 상태로 회원가입에 성공해야 한다.")
    void regTest() {
        //(1) 원본비밀번호(c+ a+ v)
        String rawPassword = "def1234!!";

        //(2) 비밀번호를 암호화인코딩하는 객체 새성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodePassword = encoder.encode(rawPassword);

        Member member = Member.builder().account("def1234").email("def111@gmail.com").name("오달수").auth(Auth.COMMON).password(encodePassword).build(); //(0)(3)

        memberMapper.register(member);
        //((1) (0)에서 원래 적어놨던 password 값을, c+a+v로 변수로 빼고, (2) 진행 -> (3) encodePassword를 password에 집어넣음.

    }

    @Test
    @DisplayName("로그인 검증을 정확하게 수행해야 한다.")
    void loginTest() {
        //로그인 시도 아이디, 비번
        String inputId = "def1234";
        String inputPw = "def1234!!";

        //로그인 시도 아이디를 기본으로 회원정보 조회
        Member member = memberMapper.getUser(inputId); //없으면 null이 옴.
        if (member != null) {
            //db에 저장된 비번 (암호화된 상태)
            String dbPw = member.getPassword();
            //암호화된 비번을 평문으로 디코딩 후 비교. (디코딩한 값을 얻어낼 순 없음. 불법임)
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공!");
            } else {
                System.out.println("비번이 틀립니다.");
            }
        } else {
            System.out.println("회원가입된 아이디가 아닙니다.");
        }

    }

    @Test
    @DisplayName(" 아이디, 이메일 중복확인에 성공해야 한다.")
    void duplicateTest() {

        String inputId = "def1234";

        int result = memberMapper.isDuplicate(inputId);

        assertEquals(1, result); // 이미 중복된 아이디 있는거 아니깐, 1일거라고 예상할때 이렇게 씀 -> 테스트 통과되면 result가 1인거


    }

    @Test
    @DisplayName(" 이메일 중복확인에 성공해야 한다.")
    void duplicateTest2() {

        String inputEmail = "def111@gmail.com";

        int result2 = memberMapper.isDuplicate2(inputEmail);

        assertTrue(result2 == 1);


    }




}
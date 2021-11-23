package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.LoginFlag;
import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    //회원가입 form 요청
    @GetMapping("/join")
    private String join() {

        return "member/join";
    }

    //id중복확인 비동기 통신요청
    @GetMapping("/check")
    @ResponseBody
    public boolean check(String checkId) {
        log.info("/check 비동기 요청 GET! " + checkId);

        return memberService.isDuplicate(checkId);
    }

    //emain중복확인 비동기 통신요청
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2(String checkEmail) {
        log.info("/check 비동기 요청 GET! " + checkEmail);

        return memberService.isDuplicate2(checkEmail);
    }

    //회원가입 요청처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST! " + member);
        //서비스한테 위임
        memberService.signUp(member); // 비밀번호 암호문으로 바꾸는 중간처리 작업 해줘야함.
        return "redirect:/"; // 일단 홈으로 가게둠.
    }

    //21.11.23
    //로그인 양식 요청
    @GetMapping("/login")
    public String login() {

        return "member/login";
    }

    //로그인 검증
    @PostMapping("/loginCheck")
    private String loginCheck(String account, String password, Model model, HttpSession session) {
        log.info("/loginCheck POST! " + " account: " +account + ", password: " + password);
        LoginFlag flag = memberService.login(account, password);
        log.info(flag);
        model.addAttribute("msg", flag);

        //로그인 성공시
        if (flag == LoginFlag.SUCCESS) { // 열거형은 == 가능. 문자열 취급x
            //모델에 담아가기
//            model.addAttribute("loginUser", memberService.getMember(account));
            //이렇게하면 안나옴. (http 프로토콜 - 무상태성, 비연결성) ↓
            /*
            [클 -서버] request하나 날리면 response 날려줌. 그럼 끝. 모든걸 잊어버림. http는 로그인 상태를 기억 못시킴.
            그래서 나온게 '쿠키, 세션'
            [클-서]
            요청할때, 로그인 성공하면 서버 너가 쿠키에 로그인했다고 써놓고, 그거 클한테 주고, 클은 하드디스크에 쿠키 저장.
            자동로그인-쿠키삭제하면 다풀림. / 특징: 쿠키는 보안에 취약함(왔다갔다거리고, 로컬에 저장되서) , 컴퓨터 포맷하면 다날라감
            쿠키 보안 위험해서 -> ' 세션 '

            [클-서]
            서버 안에 세션을 만들음. 브라우저 1개당 세션 1개.
            세션에 담기 => HttpSession session
             */
            //세션에 담기
            session.setAttribute("loginUser", memberService.getMember(account));
            return "redirect:/";
        }

        return "member/login";
    }

    //로그아웃
    @GetMapping("/sign-out")
    public String logout(HttpSession session) {
        //세션에서 로그인했던 흔적을 없애주면됨. session.setAttribute("loginUser", memberService.getMember(account)); -> 이걸 없애버림
        //우선 세션 뒤지기
        Member member = (Member) session.getAttribute("loginUser"); // getAttribute 타입이 Object라 다운캐스팅 해줘야함. (CAR 랑 SONATA 관계)
        if (member != null) {
            session.removeAttribute("loginUser");
            session.invalidate();//세션무효화 (초기상태로 되돌려버림)
        }
        return "redirect:/";
    }

}

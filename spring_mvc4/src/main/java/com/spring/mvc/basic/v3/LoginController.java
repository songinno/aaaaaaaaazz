package com.spring.mvc.basic.v3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    //로그인 양식화면을 열어주는 요청처리
    @GetMapping("/s-login-form")
    public String sLoginForm() {
        System.out.println("로그인 양식 오픈!!");
//        return "/WEB-INF/views/s-form.jsp";
        return "s-form";
    }

    //로그인 검증 요청처리
    @PostMapping("/s-login-check")
    public String sLoginCheck(String id, String pw, Model model) {
        //검증
        if (id.equals("grape")) {
            if (pw.equals("5555")) {
                model.addAttribute("result", "success");
            } else {
                model.addAttribute("result", "pwFail");
            }
        } else {
            model.addAttribute("result", "idFail");
        }
        //jsp파일 열기
        return "s-result";
    }
}

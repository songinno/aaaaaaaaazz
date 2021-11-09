package com.spring.mvc.basic.v1;

import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// # 서블릿: 웹 브라우저의 요청을 처리하여 서버에서 동적HTML을 생성해서
//         응답해주는 클래스
@WebServlet("/about")
public class AboutServlet extends HttpServlet {

    public AboutServlet() {
        System.out.println("어바웃서블릿 객체 생성됨!!!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/about 요청이 들어옴!!!");

        //URL정보 얻기
        String uri = req.getRequestURI();
        System.out.println(uri);

        //요청파라미터얻기
        String qs = req.getQueryString();
        System.out.println(qs);

        //요청 파라미터 각각 얻기
        String msg = req.getParameter("msg");
        System.out.println(msg);

        String hobby = req.getParameter("hobby");
        System.out.println(hobby);

        //웹브라우저에 html을 응답
        resp.setContentType(MediaType.TEXT_HTML_VALUE);
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();

        w.write("<html>");
        w.write("<head>");
        w.write("</head>");
        w.write("<body>");
        w.write("<h1> 당신이 보낸 메시지: " + msg + "</h1>");

        if (hobby.equals("soccer")) {
            w.write("<h2> 축구를 좋아하시네여~~ </h2>");
        } else {
            w.write("<h2> 축구말고 " + hobby + " 좋아하시네여~~ </h2>");
        }
        w.write("</body>");
        w.write("</html>");

    }
}

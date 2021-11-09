package com.spring.mvc.basic.v2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login-form")
public class LoginForm extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //WEB-INF폴더 내부의 jsp를 여는 방법
        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/form.jsp");
        dispatcher.forward(req, resp);
    }
}

package com.spring.mvc.basic.v1;

import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/work")
public class WorkServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.TEXT_HTML_VALUE);
        resp.setCharacterEncoding("utf-8");
        PrintWriter w = resp.getWriter();

        w.write("<html>");
        w.write("<head>");
        w.write("</head>");
        w.write("<body>");

        w.write("<form action='/about'>");
        w.write("# 메시지: <input name='msg'> <br>");
        w.write("# 취미: <input name='hobby'> <br>");
        w.write("<button type='submit'>전송</button>");
        w.write("</form>");

        w.write("</body>");
        w.write("</html>");




    }
}

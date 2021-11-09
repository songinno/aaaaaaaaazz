<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title> 
</head>
<body>
    
    <%
        //요청 파라미터 읽기
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        System.out.println(id);
        System.out.println(pw);

        if (id.equals("apple")) {
            if (pw.equals("1234")) {
                //로그인 성공
    %>
                <h1>로그인 성공!!</h1>
    <%
            } else {
                //비번 틀림
        %>
            <h1>비밀번호가 틀렸습니다!!</h1>
        <%
            }
        } else {
            //아이디가 없음
        %>
            <h1>회원이 존재하지 않습니다.</h1>
        <%
        }
    %>

    <a href="/hello.jsp">다시 로그인하기</a>

</body>
</html>
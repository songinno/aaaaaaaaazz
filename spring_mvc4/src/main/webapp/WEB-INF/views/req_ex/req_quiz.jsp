<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
</head>
<body>
    <form action="/req/quiz" method="post">
        <!-- "/req/quiz" 여기에 post방식으로 정보 내보냄  -->
        <div>
            <input type="text" name="userAccount" placeholder="계정명을 입력하세요.">
        </div>
        <div>
            <input type="password" name="userPassword" placeholder="비밀번호를 입력하세요.">
        </div>
        <button type="submit">로그인</button>
    </form>


<!--  
<%--
    
    1. req_quiz 파일의 화면처리를 할 수 있는 메서드를 생성하세요.
        (/req/quiz GET 맵핑)
   
    2. ID:kim123, PW:kkk1234 라면 success.jsp페이지로
         이동해서 "로그인 성공" "(회원ID)님 환영합니다" 출력
    3. 아니라면 fail.jsp페이지로 이동해서
       "로그인 실패" "(회원ID)는 회원이 아닙니다" 출력
    
    --%>
-->
    

</body>
</html>
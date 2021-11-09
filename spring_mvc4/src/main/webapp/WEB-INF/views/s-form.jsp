<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        label {
            display: block;
        }
    </style>
</head>
<body>
    
    <h1>WEB-INF/views의 s-form.jsp</h1>

    <form action="/s-login-check" method="post">
        <label>
            # 아이디 : <input type="text" name="id">
        </label>
        <label>
            # 비밀번호 : <input type="password" name="pw">
        </label>
        <label>
            <button type="submit">로그인</button>
        </label>
    </form>

</body>
</html>
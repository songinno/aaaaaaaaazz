<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>
    <h1>v1.jsp입니다.</h1>

    <form action="/req/v3" method="post">
        <label>
            <input type="radio" name="pet" value="puppy"> 멍멍이
        </label>
        <label>
            <input type="radio" name="pet" value="jerry"> 야옹이
        </label>
        
        <br>
        <label>
            # 나이: <input type="text" name="age">
        </label>

        <br>
        # 주인: <input type="text" name="master">

        <br>
        # 성별: 
        <label>
            <input type="radio" name="gender" value="female"> 암컷
        </label>
        <label>
            <input type="radio" name="gender" value="male"> 수컷
        </label>

        <br>

        <label>
            # 취미:
            <input type="checkbox" name="hobby" value="장난"> 장난
            <input type="checkbox" name="hobby" value="산책"> 산책
            <input type="checkbox" name="hobby" value="낮잠"> 낮잠
        </label>

        <button type="submit">확인</button>
    </form>


</body>
</html>
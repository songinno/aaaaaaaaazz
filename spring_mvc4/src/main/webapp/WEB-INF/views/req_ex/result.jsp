
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

    <h1>랜덤 음식: ${f}</h1>
    <h2>전체 음식: 
        <c:forEach var="fName" items="${foods}">
            <span>${fName} </span>
        </c:forEach>
    </h2>
    <h3>첫번째 음식: ${foods[0]}</h3>

</body>
</html>
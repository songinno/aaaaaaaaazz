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

    <div id="pet-info">
        <h1> # ${petName} 정보!!</h1>
        <p>
            우리 애완동물 이름은 ${petName}이구요~~~ <br>
            나이는 ${petAge}살입니다. 그리고 주인인 제 이름은 ${master}이구요~~ <br>
            ${petName}의 성별은 ${gender}입니다.
        </p>
        <h2>* ${petName}이(가) 좋아하는 것! </h2>
        <ul>
            <c:forEach var="h" items="${hobby}">
                <li> ${h} </li>
            </c:forEach>
        </ul>

    </div>

</body>

</html>
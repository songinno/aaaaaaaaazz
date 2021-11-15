<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 날짜 포맷 변경하려고 만든거 ▽ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="../include/static-head.jsp" %>

    <style>
        .board-list {
            width: 70%;
            height: 800px;
            margin: 0 auto;
        }
        .board-list .articles {

            margin: 250px auto 10px;
            border-collapse: collapse;
            font-size: 1.5em;
            border-radius: 10px;
        }
        .board-list .btn-write {
            /* background: orange; */
            text-align: right;
        }
        .board-list .articles tr td,
        .board-list .articles tr th {
            border: 1px solid gray;
            padding: 10px;
        }
       
    </style>
</head>
<body>

    <div class="wrap">

        <%@ include file="../include/header.jsp" %>

        <div class="board-list">
            <table class="table table-dark table-striped table-hover articles">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성시간</th>
                </tr>

                <c:forEach var="b" items="${articles}">
                    <tr>
                        <td>${b.boardNo}</td>
                        <td>${b.writer}</td>
                        <td>
                            ${b.title}
                            <!-- test가 true면 c:if가 작동. -->
                            <c:if test="${b.newFlag}">
                                <span class="badge rounded-pill bg-danger">new</span>
                            </c:if>
                        </td>
                        <td>${b.viewCnt}</td>
                        <td>
                            <fmt:formatDate value="${b.regDate}" pattern="yyyy년 MM월 dd일 a hh:mm" />
                            <!-- 대문자 HH로 쓰면 00~24시 -->
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="btn-write">
                <a class="btn btn-outline-danger btn-lg" href="/board/write">글쓰기</a>
            </div>
        </div>


        <%@ include file="../include/footer.jsp" %>

    </div>
    <script>
        
        // console.log('글번호' + bn);
        //상세보기 요청 이벤트
        const $table = document.querySelector(".articles");
        $table.addEventListener('click', e => {
            if(!e.target.matches('.articles td')) return;
            console.log('tr 클릭됨!- ', e.target); 

            let bn = e.target.parentElement.firstElementChild.textContent;    
            location.href= '/board/content?boardNo=' + bn;
        });
        
    </script>
</body>
</html>
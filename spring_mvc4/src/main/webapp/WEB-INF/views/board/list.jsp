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
            margin: 0 auto;
        }

        .board-list form {
            display: flex;
        }

        .board-list .search {
            position: absolute;
            top: 19%;
            left: 16%;
            display: flex;
            /* background: orange; */
        }

        .board-list .search .form-select {
            margin-right: 20px;
        }

        .board-list .search input {
            border: 1px solid #000;
        }

        .board-list .articles {
            margin: 250px auto 100px;
            border-collapse: collapse;
            font-size: 1.5em;
            border-radius: 10px;
        }

        .board-list .bottom-section {
            display: flex;
            margin-top: -50px;
            margin-bottom: 300px;
        }
        .board-list .bottom-section ul {
            flex: 9;
            display: flex;
            justify-content: center;
        }

        .pagination-custom a {
            color: #444;
        }

        .pagination-custom li.active a {
            background: #333;
            color: #fff !important;
        }


        .board-list .bottom-section .btn-write {
            flex: 1;
            /* background: orange; */
            /* text-align: right;
            position: relative;
            top: -70px; */
        }

        .board-list .amount {
            position: absolute;
            top: 21%;
            right: 16%;            
        }
        .board-list .amount a {
            width: 80px;
        }

    </style>
</head>
<body>

    <div class="wrap">

        <%@ include file="../include/header.jsp" %>

        <div class="board-list">
            <!-- 검색창 영역 -->
            <section class="search">
                <form action="/board/list" method="get">
                    <select id="search-type" class="form-select" name="type">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="writer">작성자</option>
                        <option value="titleContent">제목+내용</option>
                    </select>
                    <input class="form-control" type="text" name="keyword" value="${pageInfo.page.keyword}">
                    <button class="btn btn-primary" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
                
            </section>

            <div class="amount">
                <a class="btn btn-danger" href="/board/list?amount=10">10</a>
                <a class="btn btn-danger" href="/board/list?amount=20">20</a>
                <a class="btn btn-danger" href="/board/list?amount=30">30</a>
            </div>


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

            <div class="bottom-section">
                <!-- <div>${pageInfo}</div> -->
                <ul class="pagination pagination-lg pagination-custom">
                    <c:if test="${pageInfo.prev}">
                        <li class="page-item"><a class="page-link" 
                            href="/board/list?pageNum=${pageInfo.beginPage - 1}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">prev</a></li>
                    </c:if>
                    <!-- fori문 -->
                    <c:forEach var="i" begin="${pageInfo.beginPage}" end="${pageInfo.endPage}" step="1">
                        <!-- step은 안쓰면 1 -->
                    <li class="page-item"><a class="page-link" 
                        href="/board/list?pageNum=${i}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">${i}</a>
                    </li>
                    </c:forEach>

                    <c:if test="${pageInfo.next}">
                        <li class="page-item"><a class="page-link" 
                            href="/board/list?pageNum=${pageInfo.endPage + 1}&amount=${pageInfo.page.amount}&type=${pageInfo.page.type}&keyword=${pageInfo.page.keyword}">next</a></li>
                    </c:if>

                </ul>
                <div class="btn-write">
                    <a class="btn btn-outline-danger btn-lg" href="/board/write">글쓰기</a>
                </div>
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
            location.href= '/board/content?boardNo=' + bn 
            + '&pageNum=${pageInfo.page.pageNum}' 
            + '&amount=${pageInfo.page.amount}';
            
        });
        

        //현재 위치한 페이지에 active 클래스 부여하기
        function appendPageActive() {
            //현재 위치한 페이지 넘버
            const curPage = '${pageInfo.page.pageNum}';
            console.log('현재페이지: ', curPage);
            //ul의 li들을 전부 확인해서, 그 텍스트컨텐츠(=페이지넘버)가 현재 위치한 페이지넘버(curPage)가 같은 li에게 class="active" 부여

            const $ul = document.querySelector('.pagination');
            for (let $li of [...$ul.children]) {
                if($li.textContent === curPage) {
                    $li.classList.add('active');
                    break;
                } 
            } 
        }

        //검색 완료 후, select option값 고정 (+++21.11.17)
        function fixSearchOption() {
            const $select = document.getElementById('search-type');
            for (let $op of [...$select.children]) {
                if ($op.value === '${pageInfo.page.type}') {
                    $op.setAttribute('selected', 'selected');
                    // 논리 옵션은 값 똑같이: 'selected', 'selected'
                    break;
                }
            }
        }

        appendPageActive();
        fixSearchOption();

    </script>
</body>
</html>
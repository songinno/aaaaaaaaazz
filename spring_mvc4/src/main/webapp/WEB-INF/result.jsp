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

    <h1></h1>

    <a href="/login-form">다시 로그인하기</a>
    
    <script>
        let result = '${result}';
        console.log(result);

        const $h1 = document.querySelector('h1');
        switch (result) {
            case 'idFail': 
                $h1.textContent = '아이디가 존재하지 않습니다.';
                break;
            case 'pwFail':
                $h1.textContent = '비밀번호가 틀렸습니다.';
                break;
            case 'success':
                $h1.textContent = '로그인 성공.';
                break;        
        }

    </script>
</body>
</html>
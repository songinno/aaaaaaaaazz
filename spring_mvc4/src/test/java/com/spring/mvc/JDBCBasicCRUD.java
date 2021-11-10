package com.spring.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCBasicCRUD {

    //DB 접속 정보 설정
    private String id = "spring4";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";

    @Test
    @DisplayName("product데이터를 추가해야 한다.")
    void insertTest() {

        try {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결정보 객체 생성 (DB접속하는 코드)
            Connection conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB연결 성공! = " + conn);

            //3. SQL 실행객체 생성
            String sql = "INSERT INTO product VALUES (seq_product.nextval, ?, ?)"; // 안정해진건 물음표로
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기.
            //?: 제품명 -> 1번(인덱스) 물음표
            pstmt.setString(1, "공기청정기");
            pstmt.setInt(2, 670000);

            //5. SQL 실행 명령
            //명령문이 다름. (리턴이 다르니깐)
            //1) INSERT, UPDATE, DELETE -> executeUpdate()
            //2) SELECT -> executeQuery()
            int resultNum = pstmt.executeUpdate(); // 성공한 쿼리의 수를 리턴
            if (resultNum == 1) {
                System.out.println("입력 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("product데이터를 수정해야 한다.")
    void updateTest() {

        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결정보 객체 생성 (DB접속하는 코드)
//            Connection conn = DriverManager.getConnection(url, id, pw); // try () 안에 넣었음. -> 자동으로 자원반납시켜줌.
            System.out.println("DB연결 성공! = " + conn);

            //3. SQL 실행객체 생성
//            String sql = "UPDATE product " + "SET product_name=?, product_price=? " + "WHERE product_id=?";
            String sql = "UPDATE product SET product_name=?, product_price=? WHERE product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기.
            pstmt.setString(1, "텔레비전_fix");
            pstmt.setInt(2, 2000000);
            pstmt.setInt(3, 2); //원래 냉장고였음.

            //5. SQL 실행 명령
            //명령문이 다름. (리턴이 다르니깐)
            //1) INSERT, UPDATE, DELETE -> executeUpdate()
            //2) SELECT -> executeQuery()
            int resultNum = pstmt.executeUpdate(); // 성공한 쿼리의 수를 리턴
            if (resultNum == 1) {
                System.out.println("수정 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("product데이터를 삭제해야 한다.")
    void deleteTest() {

        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결정보 객체 생성 (DB접속하는 코드)
//            Connection conn = DriverManager.getConnection(url, id, pw); // try () 안에 넣었음. -> 자동으로 자원반납시켜줌.
            System.out.println("DB연결 성공! = " + conn);

            //3. SQL 실행객체 생성
//            String sql = "UPDATE product " + "SET product_name=?, product_price=? " + "WHERE product_id=?";
            String sql = "DELETE FROM product WHERE product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기.
            pstmt.setInt(1, 1); // 세탁기였음.

            //5. SQL 실행 명령
            //명령문이 다름. (리턴이 다르니깐)
            //1) INSERT, UPDATE, DELETE -> executeUpdate()
            //2) SELECT -> executeQuery()
            int resultNum = pstmt.executeUpdate(); // 성공한 쿼리의 수를 리턴
            System.out.println("resultNum = " + resultNum);
            if (resultNum == 1) {
                System.out.println("삭제 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///////////SELECT////////////
    @Test
    @DisplayName("product데이터를 전체 조회해야 한다.")
    void findAllTest() {

        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결정보 객체 생성 (DB접속하는 코드)
//            Connection conn = DriverManager.getConnection(url, id, pw); // try () 안에 넣었음. -> 자동으로 자원반납시켜줌.
            System.out.println("DB연결 성공! = " + conn);

            //3. SQL 실행객체 생성
//            String sql = "UPDATE product " + "SET product_name=?, product_price=? " + "WHERE product_id=?";
            String sql = "SELECT * FROM product";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기.

            //5. SQL 실행 명령
            //명령문이 다름. (리턴이 다르니깐)
            //1) INSERT, UPDATE, DELETE -> executeUpdate()
            //2) SELECT -> executeQuery()
            ResultSet rs = pstmt.executeQuery();
//            boolean b1 = rs.next();// next(): resultset 안에 있는 커서를 움직여서, 표를 읽어서 갖다줌.
//            //처음 호출하면 첫번째 행을 가르킴.
//            boolean b2 = rs.next();// 2번 쓰면 -> 커서가 2째 행으로 움직임.
//            System.out.println("b1 = " + b1);
//            System.out.println("b2 = " + b2);
////            String name = rs.getString("product_name");// 위치한 커서의 product_name을 읽음.
////            int price = rs.getInt("product_price");// 위치한 커서의 product_name을 읽음.
////            System.out.println("name = " + name); //=> name = 텔레비전_fix //=>name = 공기청정기
////            System.out.println("price = " + price);
//            //next() 리턴 타입 boolean. 지목될 행이 없으면 false. 있으면 true
//
//            boolean b3 = rs.next();
//            System.out.println("b3 = " + b3); //false

            //전체 데이터를 출력하려면, 반복시켜야함. 지목되는 행이 안나올때까지
            while(rs.next()) {
                String name = rs.getString("product_name");// 위치한 커서의 product_name을 읽음.
                int price = rs.getInt("product_price");// 위치한 커서의 product_name을 읽음.
                System.out.printf("제품명: %s, 가격: %d원\n", name, price);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}//end class

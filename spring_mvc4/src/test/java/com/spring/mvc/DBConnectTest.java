package com.spring.mvc;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DBConnectTest {

    //DB 접속 정보 설정
    private String id = "hr";
    private String pw = "hr";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    // 구글에 oracle jdbc url 검색 -> db마다 url패턴이 다름.

    @Test
    void connectTest() {
        try {
            //1. 드라이버 커넥터 동적 로딩
            //구글에 oracle driver name라고 검색
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //2. 연결정보 생성
            Connection conn = DriverManager.getConnection(url, id, pw);
            System.out.println("DB연결 성공! = " + conn);
            //3.SQL 실행하기
            String sql = "SELECT first_name, salary FROM employees";
            //PreparedStatement: SQL실행 객체
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();//-> db로 select문을 날림 // ResultSet: 표.

            while (rs.next()) {
                String name = rs.getString("first_name");
                int salary = rs.getInt("salary");
                System.out.println("name = " + name +" / "+ "salary = " + salary);
            }


        } catch (Exception e) {
            System.out.println("DB연결 실패!!");
            e.printStackTrace();
        }
    }

}

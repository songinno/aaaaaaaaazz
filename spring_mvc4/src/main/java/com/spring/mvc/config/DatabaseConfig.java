package com.spring.mvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//DB관련 설정을 세팅하는 클래스
@Configuration
@ComponentScan(basePackages = "com.spring.mvc")
public class DatabaseConfig {
    //DB접속정보 설정 (= DataSource 설정)
    @Bean
    public DataSource dataSource() {
        //커넥션 풀: 연결객체를 풀에 담아두고 재활용
        HikariConfig config = new HikariConfig();
        // 다운받은적 없는데 있음. -> //jdbc 라이브러리 implementation "org.springframework.boot:spring-boot-starter-jdbc" 여기에 담겨있음.
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe"); // 한번만 써놓으면 됨.
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setUsername("spring4");
        config.setPassword("1234");
        return new HikariDataSource(config);
    }
}

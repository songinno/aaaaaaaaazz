package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository("ssr")
@Log4j2
@RequiredArgsConstructor
public class SpringScoreRepository implements ScoreRepository {

    //JDBC의 중복코드를 처리해주는 템플릿
    private final JdbcTemplate jdbcTemplate; // 외부 라이브러리


//    public SpringScoreRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    //=> @RequiredArgsConstructor 가 만들어줌.(주입 받을게 하나만 있을때)

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO score VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql
                , score.getName(), score.getKor(), score.getEng(),score.getMath(),score.getTotal(),score.getAverage()) == 1;
    } // config에서 정보를 갖고옴.

    @Override
    public List<Score> findAll() {
        String sql = "SELECT * FROM score";
        //RowMapper 인터페이스임.
        //T mapRow(ResultSet rs, int rowNum) throws SQLException; 추상메서드 이거 하나있음.

//        return jdbcTemplate.query(sql,ScoreRowMapper);

        /*
        return jdbcTemplate.query(sql, new RowMapper<Score>() {
            @Override
            public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Score(rs);
            }
        });
         */
        //한 줄로 =>
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Score(rs));
    }

    @Override
    public Score findOne(int stuNum) {
        String sql = "SELECT * FROM score WHERE stu_num=?";
//        return jdbcTemplate.query(sql, (rs,rowNum) -> new Score(rs), stuNum).get(0));
        return jdbcTemplate.queryForObject(sql, (rs,rowNum) -> new Score(rs), stuNum);
    }

    @Override
    public boolean remove(int stuNum) {
        String sql = "DELETE FROM score WHERE stu_num=?";
        return jdbcTemplate.update(sql, stuNum) == 1;
    }
}

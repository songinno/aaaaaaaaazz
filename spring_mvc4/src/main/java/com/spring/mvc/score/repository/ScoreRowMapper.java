package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreRowMapper implements RowMapper<Score> {
    @Override
    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Score(rs);
        //한 행에 대해 정하면 List로 갖다줌.
    }
}

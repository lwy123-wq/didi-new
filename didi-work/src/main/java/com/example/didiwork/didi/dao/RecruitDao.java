package com.example.didiwork.didi.dao;

import com.example.didiwork.didi.entity.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RecruitDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Requirement SelectRecruit(int id){
        final Requirement re=new Requirement();
        String sql= "SELECT * FROM   recruitmentrequirements WHERE id=? ";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                re.setOccupationalCategory(rs.getString(3));
                re.setRemainingPositions(rs.getString(4));
                re.setSalaryRequirements(rs.getString(5));
                re.setCompany(rs.getString(2));
                re.setNumber(rs.getInt(6));
            }
        });
        return re;
    }
}

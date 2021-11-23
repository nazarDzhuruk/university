package com.foxminded.model.student;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new StudentBuilderImpl()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname")).build();
        return student;
    }
}

package com.foxminded.model.teacher;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new TeacherBuilderImpl()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname")).build();
        return teacher;
    }
}

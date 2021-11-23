package com.foxminded.model.course;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new CourseBuilderImpl()
                .setCourseID(rs.getInt("id"))
                .setCourseName(rs.getString("name")).build();
        return course;
    }
}

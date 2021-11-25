package com.foxminded.model.course.students;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentsMapper implements RowMapper<CourseStudents> {
    @Override
    public CourseStudents mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseStudents courseStudents = new CourseStudentBuilderImpl()
                .setCourseId(rs.getInt("course_id"))
                .setStudentId(rs.getInt("student_id")).build();
        return courseStudents;
    }
}

package com.foxminded.model.course.lectures;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseLectureMapper implements RowMapper<CourseLecture> {
    @Override
    public CourseLecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseLecture courseLecture = new CourseLectureBuilderImpl()
                .setCourseId(rs.getInt("course_id"))
                .setLectureId(rs.getInt("lecture_id")).build();
        return courseLecture;
    }
}

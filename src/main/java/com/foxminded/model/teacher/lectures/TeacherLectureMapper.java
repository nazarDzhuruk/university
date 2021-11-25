package com.foxminded.model.teacher.lectures;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherLectureMapper implements RowMapper<TeacherLecture> {
    @Override
    public TeacherLecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        TeacherLecture teacherLecture = new TeacherLectureBuilderImpl()
                .setTeacher(rs.getInt("teacher_id"))
                .setLectures(rs.getInt("lecture_id")).build();
        return teacherLecture;
    }
}

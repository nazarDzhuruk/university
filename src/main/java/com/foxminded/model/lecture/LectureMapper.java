package com.foxminded.model.lecture;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureMapper implements RowMapper<Lecture> {
    @Override
    public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lecture lecture = new LectureBuilderImpl()
                .setId(rs.getInt("id"))
                .setLectureName(rs.getString("lecturename"))
                .setLectureStartTime(rs.getTime("starttime")).build();
        return lecture;
    }
}

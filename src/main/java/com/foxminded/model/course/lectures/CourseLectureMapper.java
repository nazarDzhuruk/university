package com.foxminded.model.course.lectures;

import com.foxminded.exception.UniversityRowMapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseLectureMapper implements RowMapper<CourseLecture> {
    private static final Logger logger = LoggerFactory.getLogger(CourseLectureMapper.class);

    @Override
    public CourseLecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.debug("Start CourseLectureRowMapper...");
        try {
            CourseLecture courseLecture = new CourseLectureBuilderImpl()
                    .setCourseId(rs.getInt("course_id"))
                    .setLectureId(rs.getInt("lecture_id")).build();
            return courseLecture;
        }catch (SQLException e){
            String error = "Error mapping CourseLecture";
            logger.error(error);
            throw new UniversityRowMapperException(error, e);
        }
    }
}

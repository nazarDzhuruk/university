package com.foxminded.model.course;

import com.foxminded.exception.UniversityRowMapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    private static final Logger logger = LoggerFactory.getLogger(CourseMapper.class);
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.debug("Start CourseMapper...");
        try {
            Course course = new CourseBuilderImpl()
                    .setCourseID(rs.getInt("courseid"))
                    .setCourseName(rs.getString("coursename")).build();
            return course;
        }catch (SQLException e){
            String error = "Error mapping";
            logger.error(error);
            throw new UniversityRowMapperException(error, e);
        }
    }
}

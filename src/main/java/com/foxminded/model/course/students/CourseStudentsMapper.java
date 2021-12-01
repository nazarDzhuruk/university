package com.foxminded.model.course.students;

import com.foxminded.exception.UniversityDaoException;
import com.foxminded.exception.UniversityRowMapperException;
import com.foxminded.exception.UniversityServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentsMapper implements RowMapper<CourseStudents> {
    private static final Logger logger = LoggerFactory.getLogger(CourseStudentsMapper.class);

    @Override
    public CourseStudents mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.debug("Start CourseStudentsMapper...");
        try {
            CourseStudents courseStudents = new CourseStudentBuilderImpl()
                    .setCourseId(rs.getInt("course_id"))
                    .setStudentId(rs.getInt("student_id")).build();
            return courseStudents;
        }catch (SQLException e){
            String error = "Error mapping";
            logger.error(error);
            throw new UniversityRowMapperException(error, e);
        }
    }
}

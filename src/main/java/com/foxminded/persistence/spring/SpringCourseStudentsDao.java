package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseStudentDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.course.students.CourseStudents;
import com.foxminded.model.course.students.CourseStudentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class SpringCourseStudentsDao implements CourseStudentDao {
    private static Logger logger = LoggerFactory.getLogger(SpringCourseStudentsDao.class);

    private final JdbcTemplate jdbcTemplate;

    public SpringCourseStudentsDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addStudents(int studentId, int courseId) {
        logger.debug("Creating relation student_course...");
        String sql = "INSERT INTO course_student (student_id, course_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, studentId, courseId);
        } catch (BadSqlGrammarException e) {
            String msg = "Student or course does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<CourseStudents> index() {
        String sql = "SELECT * FROM course_student";
        return jdbcTemplate.query(sql, new CourseStudentsMapper());
    }
}

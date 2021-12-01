package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.course.Course;
import com.foxminded.model.course.CourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class SpringCourseDao implements CourseDao {
    private static Logger logger = LoggerFactory.getLogger(SpringCourseDao.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringCourseDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Course course) {
        logger.debug("Creating course");
        String sql = "INSERT INTO course VALUES(?,?)";
        if (course == null) {
            String msg = "Course is null";
            logger.warn(msg);
            throw new UniversityDaoException(msg);
        }
        try {
            jdbcTemplate.update(sql, course.getCourseID(), course.getCourseName());
        } catch (DuplicateKeyException e) {
            String msg = "Course already exist!";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public Optional<Course> read(int id) {
        logger.debug("Getting data from database...");
        String sql = "SELECT * FROM course WHERE id=?";
        Course course;
        try {
            course = jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            String msg = "Course not found ";
            logger.info(msg);
            throw new UniversityDaoException(msg);
        } catch (DataAccessException e) {
            String msg = "Unable to get";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void delete(int id) {

        logger.debug("Deleting...");
        String sql = "DELETE FROM course WHERE id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (BadSqlGrammarException e) {
            String msg = "Course does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public void update(Course course) {
        logger.debug("Updating...");
        String sql = "UPDATE course SET coursename=? WHERE courseid=?";
        try {
            jdbcTemplate.update(sql, course.getCourseName(), course.getCourseID());
        } catch (BadSqlGrammarException e) {
            String msg = "Course does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<Course> index() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseMapper());
    }
}

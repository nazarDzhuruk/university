package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseDao;
import com.foxminded.model.course.Course;
import com.foxminded.model.course.CourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        String sql = "INSERT INTO course VALUES(?,?)";
        int insert = jdbcTemplate.update(sql, course.getCourseID(), course.getCourseName());
        if (insert == 1) {
            logger.info("New course added " + course.toString());
        }
    }

    @Override
    public Optional<Course> read(int id) {
        String sql = "SELECT * FROM course WHERE id=?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseMapper());
        } catch (DataAccessException exception) {
            logger.info("Course not found " + id);
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM course WHERE id=?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            logger.info("Course has been deleted");
        }
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE course SET coursename=? WHERE courseid=?";
        int update = jdbcTemplate.update(sql, course.getCourseName(), course.getCourseID());
        if (update == 1) {
            logger.info("Course info has been updated");
        }
    }

    @Override
    public List<Course> index() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public void addStudents(int studentId, int courseId) {
        String sql = "INSERT INTO course_student (student_id, course_id) VALUES (?, ?)";
        int insert = jdbcTemplate.update(sql, studentId, courseId);
        if(insert == 1){
            logger.info("Assigned to course successfully");
        }
    }

    @Override
    public void addLectures(int lectureId, int courseId) {
        String sql = "INSERT INTO course_lecture (lectureId, course_id) VALUES (?, ?)";
        int insert = jdbcTemplate.update(sql, lectureId, courseId);
        if(insert == 1){
            logger.info("Assigned to course successfully");
        }
    }
}

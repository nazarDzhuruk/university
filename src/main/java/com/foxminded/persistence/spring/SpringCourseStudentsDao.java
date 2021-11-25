package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseStudentDao;
import com.foxminded.model.course.students.CourseStudents;
import com.foxminded.model.course.students.CourseStudentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        String sql = "INSERT INTO course_student (student_id, course_id) VALUES (?, ?)";
        int insert = jdbcTemplate.update(sql, studentId, courseId);
        if(insert == 1){
            logger.info("Assigned to course successfully");
        }
    }

    @Override
    public List<CourseStudents> index() {
        String sql = "SELECT * FROM course_student";
        return jdbcTemplate.query(sql, new CourseStudentsMapper());
    }
}

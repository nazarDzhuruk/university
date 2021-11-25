package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseLectureDao;
import com.foxminded.model.course.lectures.CourseLecture;
import com.foxminded.model.course.lectures.CourseLectureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class SpringCourseLectureDao implements CourseLectureDao {
    private static Logger logger = LoggerFactory.getLogger(SpringCourseLectureDao.class);
    private final JdbcTemplate jdbcTemplate;

    public SpringCourseLectureDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addLectures(int lectureId, int courseId) {
        String sql = "INSERT INTO course_lecture (course_id, lecture_id) VALUES (?, ?)";
        int insert = jdbcTemplate.update(sql, courseId, lectureId);
        if(insert == 1){
            logger.info("Assigned to course successfully");
        }
    }

    @Override
    public List<CourseLecture> index() {
        String sql = "SELECT * FROM course_lecture";

        return jdbcTemplate.query(sql, new CourseLectureMapper());
    }
}

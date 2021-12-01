package com.foxminded.persistence.spring;

import com.foxminded.dao.CourseLectureDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.course.lectures.CourseLecture;
import com.foxminded.model.course.lectures.CourseLectureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
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
        logger.debug("Creating relation lecture_course...");
        String sql = "INSERT INTO course_lecture (course_id, lecture_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, courseId, lectureId);
        } catch (BadSqlGrammarException e) {
            String msg = "Lecture or student does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<CourseLecture> index() {
        String sql = "SELECT * FROM course_lecture";

        return jdbcTemplate.query(sql, new CourseLectureMapper());
    }
}

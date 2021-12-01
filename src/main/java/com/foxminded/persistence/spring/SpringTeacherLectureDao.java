package com.foxminded.persistence.spring;

import com.foxminded.dao.TeachersLectureDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.teacher.lectures.TeacherLecture;
import com.foxminded.model.teacher.lectures.TeacherLectureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class SpringTeacherLectureDao implements TeachersLectureDao {
    private static Logger logger = LoggerFactory.getLogger(SpringTeacherLectureDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringTeacherLectureDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void assignLecture(int teacherId, int lectureId) {
        String sql = "INSERT INTO teacher_lecture (teacher_id, lecture_id) VALUES (?, ?)";
        try {
            jdbcTemplate.update(sql, teacherId, lectureId);
        }catch (BadSqlGrammarException e){
            String msg = "Teacher or lecture not found";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<TeacherLecture> index() {
        String sql = "SELECT * FROM teacher_lecture";
        return jdbcTemplate.query(sql, new TeacherLectureMapper());
    }
}

package com.foxminded.persistence.spring;

import com.foxminded.dao.LectureDao;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.lecture.LectureMapper;
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
public class SpringLectureDao implements LectureDao {
    private static Logger logger = LoggerFactory.getLogger(SpringLectureDao.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringLectureDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Lecture lecture) {
        String sql = "INSERT INTO lecture VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql, lecture.getID(), lecture.getLectureName(), lecture.getStartTime());
        if (insert == 1) {
            logger.info("New course added " + lecture.toString());
        }
    }

    @Override
    public Optional<Lecture> read(int id) {
        String sql = "SELECT * FROM lecture WHERE id=?";
        Lecture lecture = null;
        try {
            lecture = jdbcTemplate.queryForObject(sql, new Object[]{id}, new LectureMapper());
        } catch (DataAccessException exception) {
            logger.info("Lecture not found " + id);
        }
        return Optional.ofNullable(lecture);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM lecture WHERE id=?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            logger.info("Lecture has been deleted");
        }
    }

    @Override
    public void update(Lecture lecture) {
        String sql = "UPDATE lecture SET lecturename=?, starttime=? WHERE id=?";
        int update = jdbcTemplate.update(sql, lecture.getLectureName(), lecture.getStartTime(), lecture.getID());
        if (update == 1) {
            logger.info("Lecture info has been updated");
        }
    }

    @Override
    public List<Lecture> index() {
        String sql = "SELECT * FROM lecture";
        return jdbcTemplate.query(sql, new LectureMapper());
    }
}

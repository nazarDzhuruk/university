package com.foxminded.persistence.spring;

import com.foxminded.dao.LectureDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.lecture.LectureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
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
        logger.debug("Add status: in progress...");
        if (lecture == null) {
            String exception = "Lecture is null";
            logger.warn(exception);
            throw new UniversityDaoException(exception);
        }
        String sql = "INSERT INTO lecture VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql, lecture.getID(), lecture.getLectureName(), lecture.getStartTime());
        } catch (DataAccessException e) {
            String duplicate = "Lecture already exist";
            logger.warn(duplicate);
            throw new UniversityDaoException(duplicate);
        }
        logger.info("Lecture has been added");
    }

    @Override
    public Optional<Lecture> read(int id) {
        logger.debug("Read from database...");
        String sql = "SELECT * FROM lecture WHERE id=?";
        Lecture lecture;
        try {
            lecture = jdbcTemplate.queryForObject(sql, new LectureMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            String msg = "Lecture not found";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        } catch (DataAccessException exception) {
            logger.info("Unable to get");
            throw new UniversityDaoException(exception);

        }
        return Optional.ofNullable(lecture);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting...");
        String sql = "DELETE FROM lecture WHERE id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (BadSqlGrammarException e) {
            String msg = "Lecture does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public void update(Lecture lecture) {
        String sql = "UPDATE lecture SET lecturename=?, starttime=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, lecture.getLectureName(), lecture.getStartTime(), lecture.getID());
        } catch (BadSqlGrammarException e) {
            String msg = "Lecture has not been updated";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<Lecture> index() {
        String sql = "SELECT * FROM lecture";
        return jdbcTemplate.query(sql, new LectureMapper());
    }
}

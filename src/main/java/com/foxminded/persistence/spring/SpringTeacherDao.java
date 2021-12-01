package com.foxminded.persistence.spring;

import com.foxminded.dao.TeacherDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.model.teacher.TeacherMapper;
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
public class SpringTeacherDao implements TeacherDao {
    private static Logger logger = LoggerFactory.getLogger(SpringTeacherDao.class);

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public SpringTeacherDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Teacher teacher) {
        logger.debug("Add teacher status: in progress...");
        if (teacher == null) {
            String error = "Add teacher status: Error, teacher is null.";
            logger.warn(error);
            throw new UniversityDaoException(error);
        }
        String sql = "INSERT INTO teacher VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql, teacher.getID(), teacher.getName(), teacher.getSurname());
        } catch (DuplicateKeyException e) {
            String duplicate = "Teacher id already exist";
            logger.warn(duplicate);
            throw new UniversityDaoException(duplicate);
        }
        logger.info("Add teacher status: Teacher has been added");
    }

    @Override
    public Optional<Teacher> read(int id) {
        logger.debug("Read from database status: in progress... ");
        String sql = "SELECT * FROM teacher WHERE id=?";
        Teacher teacher;
        try {
            teacher = jdbcTemplate.queryForObject(sql, new TeacherMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            String error = "Could not find teacher with this id";
            logger.warn(error);
            throw new UniversityDaoException(error, e);
        } catch (DataAccessException exception) {
            String throwException = "Teacher not found";
            logger.warn(throwException);
            throw new UniversityDaoException(throwException, exception);
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting...");
        String sql = "DELETE FROM teacher WHERE id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (BadSqlGrammarException e) {
            String msg = "Teacher does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public void update(Teacher teacher) {

        String sql = "UPDATE teacher SET name=?, surname=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, teacher.getName(), teacher.getSurname(), teacher.getID());
        } catch (BadSqlGrammarException e) {
            String msg = "Teacher has not been updated";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public List<Teacher> index() {
        logger.debug("Getting all teachers...");
        String sql = "SELECT * FROM teacher";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }
}

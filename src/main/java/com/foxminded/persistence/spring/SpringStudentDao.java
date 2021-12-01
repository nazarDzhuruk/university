package com.foxminded.persistence.spring;

import com.foxminded.dao.StudentDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.model.student.Student;
import com.foxminded.model.student.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class SpringStudentDao implements StudentDao {
    private static Logger logger = LoggerFactory.getLogger(SpringStudentDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired

    public void setDataSource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Student student) {
        logger.debug("Add student status: in progress... ");
        if (student == null) {
            String exception = "Add student status: student is null!";
            logger.warn(exception);
            throw new UniversityDaoException(exception);
        }
        String sql = "INSERT INTO student VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql, student.getID(), student.getName(), student.getSurname());
        } catch (DuplicateKeyException e) {
            String duplicate = "Student already exist";
            logger.warn(duplicate);
            throw new UniversityDaoException(duplicate);
        }
        logger.info("Add student status: added successfully. ");
    }

    @Override
    public Optional<Student> read(int id) {
        logger.debug("Read from database status: in progress... ");
        String sql = "SELECT * FROM student WHERE id=?";
        Student student;
        try {
            student = jdbcTemplate.queryForObject(sql, new StudentMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            String error = "Student not found";
            logger.warn(error);
            throw new UniversityDaoException(error, e);
        } catch (DataAccessException exception) {
            String throwE = "Unable to get";
            logger.warn(throwE + id);
            throw new UniversityDaoException(throwE, exception);
        }
        logger.info("Success read file");
        return Optional.ofNullable(student);
    }

    @Override
    public void delete(int id) {
        logger.debug("Deleting...");
        String sql = "DELETE FROM student WHERE id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (BadSqlGrammarException e) {
            String msg = "Student does not exist";
            logger.warn(msg);
            throw new UniversityDaoException(msg, e);
        }
    }

    @Override
    public void update(Student student) {
        logger.debug("Updating student...");
        String sql = "UPDATE student SET name=?, surname=? WHERE id=?";
        try {
            jdbcTemplate.update(sql, student.getName(), student.getSurname(), student.getID());
        } catch (BadSqlGrammarException e) {
            String msg = "Student has not been updated";
            logger.warn(msg);
            throw new UniversityDaoException(msg);
        }
    }

    @Override
    public List<Student> index() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

}

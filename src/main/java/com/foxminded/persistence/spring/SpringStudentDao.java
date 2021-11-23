package com.foxminded.persistence.spring;

import com.foxminded.dao.StudentDao;
import com.foxminded.model.student.Student;
import com.foxminded.model.student.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class SpringStudentDao implements StudentDao {
    private static Logger logger = LoggerFactory.getLogger(SpringStudentDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO student VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql, student.getID(), student.getName(), student.getSurname());
        if (insert == 1) {
            logger.info("New student added " + student.toString());
        }
    }

    @Override
    public Optional<Student> read(int id) {
        String sql = "SELECT * FROM student WHERE id=?";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
        } catch (DataAccessException exception) {
            logger.info("Student not found " + id);
        }
        return Optional.ofNullable(student);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1) {
            logger.info("Student has been deleted");
        }
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET name=?, surname=? WHERE id=?";
        int update = jdbcTemplate.update(sql, student.getName(), student.getSurname(), student.getID());
        if (update == 1) {
            logger.info("Student info has been updated");
        }
    }

    @Override
    public List<Student> index() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

}

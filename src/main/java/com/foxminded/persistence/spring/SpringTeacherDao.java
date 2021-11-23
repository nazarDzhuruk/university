package com.foxminded.persistence.spring;

import com.foxminded.dao.TeacherDao;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.model.teacher.TeacherMapper;
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
public class SpringTeacherDao implements TeacherDao {
    private static Logger logger = LoggerFactory.getLogger(SpringTeacherDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringTeacherDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Teacher teacher) {
        String sql = "INSERT INTO teacher VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql, teacher.getID(), teacher.getName(), teacher.getSurname());
        if (insert == 1){
            logger.info("Teacher has been added");
        }
    }

    @Override
    public Optional<Teacher> read(int id) {
        String sql = "SELECT * FROM teacher WHERE id=?";
        Teacher teacher = null;
        try {
            teacher = jdbcTemplate.queryForObject(sql, new Object[]{id}, new TeacherMapper());
        }catch (DataAccessException exception){
            logger.info("Teacher not found: " + id);
        }
        return Optional.ofNullable(teacher);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM teacher WHERE id=?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 1){
            logger.info("Teacher has been deleted");
        }
    }

    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE teacher SET name=?, surname=? WHERE id=?";
        int update = jdbcTemplate.update(sql, teacher.getName(), teacher.getSurname(), teacher.getID());
        if(update == 1){
            logger.info("Teacher has been updated");
        }
    }

    @Override
    public List<Teacher> index() {
        String sql = "SELECT * FROM teacher";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }
}

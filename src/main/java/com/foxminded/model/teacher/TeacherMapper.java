package com.foxminded.model.teacher;

import com.foxminded.exception.UniversityRowMapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    private static final Logger logger = LoggerFactory.getLogger(TeacherMapper.class);

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.debug("Start TeacherMapper...");
        try {
            Teacher teacher = new TeacherBuilderImpl()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setSurname(rs.getString("surname")).build();
            return teacher;
        }
        catch (SQLException e){
            String error = "Error mapping";
            logger.error(error);
            throw new UniversityRowMapperException(error, e);
        }
    }
}

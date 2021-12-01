package com.foxminded.model.lecture;

import com.foxminded.exception.UniversityRowMapperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LectureMapper implements RowMapper<Lecture> {
    private static final Logger logger = LoggerFactory.getLogger(LectureMapper.class);

    @Override
    public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.debug("Start LectureMapper...");
        try {
            Lecture lecture = new LectureBuilderImpl()
                    .setId(rs.getInt("id"))
                    .setLectureName(rs.getString("lecturename"))
                    .setLectureStartTime(rs.getTime("starttime")).build();
            return lecture;
        }
        catch(SQLException e){
            String error = "Error mapping";
            logger.error(error);
            throw new UniversityRowMapperException(error, e);
        }
    }
}

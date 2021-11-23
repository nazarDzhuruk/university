package com.foxminded.service;

import com.foxminded.dao.StudentDao;
import com.foxminded.model.student.Student;
import com.foxminded.service.logic.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(Student student) {
        logger.debug("Start adding teacher: " + student.getID());
        if (student == null) {
            String error = "Cannot create, please enter student info";
            logger.warn(error);
        }
        try {
            studentDao.create(student);
            logger.info("Student has been added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudent(int id) {
        logger.debug("Start removing student: ");
        try {
            studentDao.delete(id);
            logger.info("Student has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

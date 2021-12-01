package com.foxminded.service;

import com.foxminded.dao.CourseLectureDao;
import com.foxminded.dao.CourseStudentDao;
import com.foxminded.dao.LectureDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.exception.UniversityServiceException;
import com.foxminded.model.course.lectures.CourseLecture;
import com.foxminded.model.course.students.CourseStudents;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.service.logic.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final StudentDao studentDao;
    private final LectureDao lectureDao;
    private final CourseStudentDao courseStudentDao;
    private final CourseLectureDao courseLectureDao;

    public StudentServiceImpl(StudentDao studentDao, LectureDao lectureDao, CourseStudentDao courseStudentDao, CourseLectureDao courseLectureDao) {
        this.studentDao = studentDao;
        this.lectureDao = lectureDao;
        this.courseStudentDao = courseStudentDao;
        this.courseLectureDao = courseLectureDao;
    }

    @Override
    public void addStudent(Student student) {
        logger.debug("Service start add teacher");
        try {
            studentDao.create(student);
        } catch (UniversityDaoException e) {
            String msg = "Student has not been created";
            logger.warn(msg);
            throw new UniversityServiceException(msg, e);
        }
        logger.info("Student has been created");
    }

    @Override
    public void removeStudent(int id) {
        logger.debug("Service start delete student...");
        try {
            studentDao.delete(id);
        } catch (UniversityDaoException e) {
            String msg = "Student has not been removed";
            logger.warn(msg);
            throw new UniversityServiceException(msg, e);
        }
    }

    @Override
    public Student findStudent(int id) {
        logger.debug("Service start find student...");
        Student student = studentDao.read(id).orElse(null);

        CourseStudents courseStudents = courseStudentDao.index().stream()
                .filter(s -> s.getStudentId() == id).findAny()
                .orElseThrow(() -> {
                    String exception = "Student not found";
                    logger.warn(exception);
                    return new UniversityServiceException(exception);
                });

        List<CourseLecture> courseLecture = courseLectureDao.index().stream()
                .filter(l -> l.getCourseId() == courseStudents.getCourseId())
                .collect(Collectors.toList());

        List<Lecture> filtered = new ArrayList<>();
        for (Lecture lectureFiltered : lectureDao.index()) {
            for (CourseLecture lectures : courseLecture) {
                if (lectureFiltered.getID() == lectures.getLectureId()) {
                    filtered.add(lectureFiltered);
                }
            }
        }
        logger.info("Student has been found");
        student.getLectures().addAll(filtered);
        return student;
    }

}

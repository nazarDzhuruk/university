package com.foxminded.service;

import com.foxminded.dao.CourseLectureDao;
import com.foxminded.dao.CourseStudentDao;
import com.foxminded.dao.LectureDao;
import com.foxminded.dao.StudentDao;
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

    @Override
    public Student findStudent(int id) {
        logger.debug("Finding student...");
        Student student = studentDao.read(id).orElse(null);

        CourseStudents courseStudents = courseStudentDao.index().stream()
                .filter(s -> s.getStudentId() == id).findAny().orElse(null);

        List<CourseLecture> courseLecture = courseLectureDao.index().stream()
                .filter(l -> l.getCourseId() == courseStudents.getCourseId())
                .collect(Collectors.toList());

        List<Lecture> lecture = lectureDao.index();

        List<Lecture> filtered = new ArrayList<>();

        for (Lecture lectureFiltered : lecture) {
            for (CourseLecture lectures : courseLecture) {
                if (lectureFiltered.getID() == lectures.getLectureId()) {
                    filtered.add(lectureFiltered);
                }
            }
        }
        student.getLectures().addAll(filtered);
        return student;
    }

}

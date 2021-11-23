package com.foxminded.service;

import com.foxminded.dao.CourseDao;
import com.foxminded.dao.LectureDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.model.course.Course;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.service.logic.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final StudentDao studentDao;
    private final LectureDao lectureDao;
    private final CourseDao courseDao;

    public CourseServiceImpl(StudentDao studentDao, CourseDao courseDao, LectureDao lectureDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.lectureDao = lectureDao;
    }

    @Override
    public void addLectureToCourse(int lectureId, int courseId) {
        logger.debug("Adding lecture to course");
        try {
            Lecture lecture = lectureDao.index().stream()
                    .filter(l -> l.getID() == lectureId).findAny().orElse(null);
            Course course = courseDao.index().stream()
                    .filter(c -> c.getCourseID() == courseId).findAny().orElse(null);
            if (lecture != null && course != null) {
//                studentDao.addCourse(lecture.getID(), course.getCourseID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        logger.debug("Adding student to course");
        try {
            Student student = studentDao.index().stream()
                    .filter(s -> s.getID() == studentId).findAny().orElse(null);
            Course course = courseDao.index().stream()
                    .filter(c -> c.getCourseID() == courseId).findAny().orElse(null);
            if (student != null && course != null) {
                courseDao.addStudents(student.getID(), course.getCourseID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void removeCourse(int courseId) {

    }
}

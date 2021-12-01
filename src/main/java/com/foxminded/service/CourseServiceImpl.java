package com.foxminded.service;

import com.foxminded.dao.*;
import com.foxminded.exception.UniversityServiceException;
import com.foxminded.model.course.Course;
import com.foxminded.model.course.lectures.CourseLecture;
import com.foxminded.model.course.students.CourseStudents;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.service.logic.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final StudentDao studentDao;
    private final LectureDao lectureDao;
    private final CourseDao courseDao;
    private final CourseLectureDao courseLectureDao;
    private final CourseStudentDao courseStudentDao;

    public CourseServiceImpl(StudentDao studentDao, CourseDao courseDao, LectureDao lectureDao,
                             CourseLectureDao courseLectureDao, CourseStudentDao courseStudentDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.lectureDao = lectureDao;
        this.courseLectureDao = courseLectureDao;
        this.courseStudentDao = courseStudentDao;
    }

    @Override
    public void addLectureToCourse(int lectureId, int courseId) {
        logger.debug("Adding lecture to course");
        Lecture lecture = lectureDao.index().stream()
                .filter(l -> l.getID() == lectureId).findAny()
                .orElseThrow(() -> new UniversityServiceException("Lecture not fund"));
        Course course = courseDao.index().stream()
                .filter(c -> c.getCourseID() == courseId).findAny()
                .orElseThrow(() -> new UniversityServiceException("Course not found"));
        courseLectureDao.addLectures(lecture.getID(), course.getCourseID());
        logger.info("Lecture has been added");

    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        logger.debug("Adding student to course");
        Student student = studentDao.index().stream()
                .filter(s -> s.getID() == studentId).findAny()
                .orElseThrow(() -> new UniversityServiceException("Student not found"));
        Course course = courseDao.index().stream()
                .filter(c -> c.getCourseID() == courseId).findAny()
                .orElseThrow(() -> new UniversityServiceException("Course not found"));
        if (student != null && course != null) {
            courseStudentDao.addStudents(student.getID(), course.getCourseID());
            logger.info("Student has been added");
        }
    }

    @Override
    public void addCourse(Course course) {
        logger.debug("Start adding course: " + course.toString());
        if (course == null) {
            String error = "Cannot create, course is null";
            logger.warn(error);
        }
        try {
            courseDao.create(course);
            logger.info("Course has been added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCourse(int courseId) {
        logger.debug("Start removing teacher: ");
        try {
            courseDao.delete(courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course findCourse(int courseId) {
        Course course = courseDao.index().stream()
                .filter(c -> c.getCourseID() == courseId).findAny().orElseThrow(() -> {
                    String msg = "Course not found";
                    logger.warn(msg);
                    throw new UniversityServiceException(msg);
                });
        List<Lecture> lectures = assignedLectures(courseId);
        List<Student> students = assignedStudents(courseId);
        course.getLecture().addAll(lectures);
        course.getStudents().addAll(students);
        return course;
    }

    private List<Lecture> assignedLectures(int courseId) {
        List<CourseLecture> courseLectures = courseLectureDao.index().stream()
                .filter(l -> l.getCourseId() == courseId).collect(Collectors.toList());
        if (courseLectures.size() == 0) {
            String exception = "This course has not assigned lectures";
            logger.warn(exception);
            throw new UniversityServiceException(exception);
        }
        List<Lecture> assigned = new ArrayList<>();
        for (Lecture lecture : lectureDao.index()) {
            for (CourseLecture courseLecture : courseLectures) {
                if (lecture.getID() == courseLecture.getLectureId()) {
                    assigned.add(lecture);
                }
            }
        }
        return assigned;
    }

    private List<Student> assignedStudents(int courseId) {
        List<CourseStudents> courseStudents = courseStudentDao.index().stream()
                .filter(c -> c.getCourseId() == courseId).collect(Collectors.toList());
        if (courseStudents.size() == 0) {
            String exception = "This course has not assigned students";
            logger.warn(exception);
            throw new UniversityServiceException(exception);
        }
        List<Student> assigned = new ArrayList<>();
        for (Student student : studentDao.index()) {
            for (CourseStudents courseStudent : courseStudents) {
                if (student.getID() == courseStudent.getStudentId()) {
                    assigned.add(student);
                }
            }
        }
        return assigned;
    }
}

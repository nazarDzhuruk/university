package com.foxminded.service;

import com.foxminded.dao.*;
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
        try {
            Lecture lecture = lectureDao.index().stream()
                    .filter(l -> l.getID() == lectureId).findAny().orElse(null);
            Course course = courseDao.index().stream()
                    .filter(c -> c.getCourseID() == courseId).findAny().orElse(null);
            if (lecture != null && course != null) {
                courseLectureDao.addLectures(lecture.getID(), course.getCourseID());
                logger.info("Lecture has been added");
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
                courseStudentDao.addStudents(student.getID(), course.getCourseID());
                logger.info("Student has been added");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            logger.info("Course has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Course findCourse(int courseId) {
        Course course = courseDao.index().stream()
                .filter(c -> c.getCourseID() == courseId).findAny().orElse(null);

        List<Lecture> lectures = assignedLectures(courseId);
        List<Student> students = assignedStudents(courseId);

        course.getLecture().addAll(lectures);
        course.getStudents().addAll(students);
        return course;
    }

    private List<Lecture> assignedLectures(int courseId) {
        List<CourseLecture> courseLectures = courseLectureDao.index().stream()
                .filter(l -> l.getCourseId() == courseId).collect(Collectors.toList());
        List<Lecture> lectures = lectureDao.index();

        List<Lecture> assigned = new ArrayList<>();
        for (Lecture lecture : lectures) {
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
        List<Student> students = studentDao.index();

        List<Student> assigned = new ArrayList<>();
        for (Student student : students) {
            for (CourseStudents courseStudent : courseStudents) {
                if (student.getID() == courseStudent.getStudentId()) {
                    assigned.add(student);
                }
            }
        }
        return assigned;
    }
}

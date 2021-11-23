package com.foxminded.service;

import com.foxminded.dao.CourseDao;
import com.foxminded.dao.LectureDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.TeacherDao;
import com.foxminded.model.course.Course;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.model.teacher.TeacherBuilderImpl;
import org.springframework.stereotype.Service;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final CourseDao courseDao;
    private final LectureDao lectureDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    public UniversityServiceImpl(CourseDao courseDao, LectureDao lectureDao, StudentDao studentDao, TeacherDao teacherDao) {
        this.courseDao = courseDao;
        this.lectureDao = lectureDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherDao.create(teacher);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.create(student);
    }

    @Override
    public void addLecture(Lecture lecture) {
        lectureDao.create(lecture);
    }

    @Override
    public void addCourse(Course course) {
        courseDao.create(course);
    }

    @Override
    public Student addStudentToCourse(Student student, Course course) {
        return null;
    }

    @Override
    public Teacher addTeacherLecture(Teacher teacher, Lecture lecture) {
        teacher = new TeacherBuilderImpl().setLecture(lecture).build();
        teacherDao.update(teacher);
        return teacher;
    }

    @Override
    public void removeTeacher(int id) {
        teacherDao.delete(id);
    }

    @Override
    public void removeLecture(int id) {
        lectureDao.delete(id);
    }

    @Override
    public void removeStudent(int id) {
        studentDao.delete(id);
    }

    @Override
    public void removeCourse(int id) {
        courseDao.delete(id);
    }
}

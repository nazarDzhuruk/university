package com.foxminded.service;

import com.foxminded.model.course.Course;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.model.teacher.Teacher;


public interface UniversityService {

    void addTeacher(Teacher teacher);

    void addStudent(Student student);

    void addLecture(Lecture lecture);

    void addCourse(Course course);

    Student addStudentToCourse(Student student, Course course);

    Teacher addTeacherLecture(Teacher teacher, Lecture lecture);

    void removeTeacher(int id);

    void removeLecture(int id);

    void removeStudent(int id);

    void removeCourse(int id);
}

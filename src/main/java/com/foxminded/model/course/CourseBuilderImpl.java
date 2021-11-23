package com.foxminded.model.course;

import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;

import java.util.HashSet;
import java.util.Set;

public class CourseBuilderImpl implements CourseBuilder{
    private String courseName;
    private int courseID;
    private Set<Student> student = new HashSet<>();
    private Set<Lecture> lecture = new HashSet<>();

    @Override
    public CourseBuilder setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    @Override
    public CourseBuilder setCourseID(int courseID) {
        this.courseID = courseID;
        return this;
    }

    @Override
    public CourseBuilder setStudent(Set<Student> student) {
        this.student = student;
        return this;
    }

    @Override
    public CourseBuilder setLecture(Set<Lecture> lecture) {
        this.lecture = lecture;
        return this;
    }

    @Override
    public Course build() {
        Course course = new Course(courseName,courseID, student, lecture);
        return course;
    }
}

package com.foxminded.model.course;

import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;

import java.util.Set;

public interface CourseBuilder {
    CourseBuilder setCourseName(String courseName);
    CourseBuilder setCourseID(int CourseID);
    CourseBuilder setStudent(Set<Student> student);
    CourseBuilder setLecture(Set<Lecture> lecture);
    Course build();
}

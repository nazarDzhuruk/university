package com.foxminded.service.logic;

import com.foxminded.model.course.Course;

public interface CourseService {
    void addLectureToCourse(int lectureId, int courseId);

    void addStudentToCourse(int studentId, int courseId);

    void addCourse(Course course);

    void removeCourse(int courseId);
}

package com.foxminded.dao;

import com.foxminded.model.course.Course;

public interface CourseDao extends SimpleDao<Course> {
    void addStudents(int studentId, int courseId);
    void addLectures(int lectureId, int courseId);
}

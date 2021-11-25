package com.foxminded.dao;

import com.foxminded.model.course.students.CourseStudents;

import java.util.List;

public interface CourseStudentDao {
    void addStudents(int studentId, int courseId);
    List<CourseStudents> index();
}

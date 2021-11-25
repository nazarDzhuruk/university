package com.foxminded.model.course.students;

public interface CourseStudentsBuilder {
    CourseStudentsBuilder setCourseId(int courseId);
    CourseStudentsBuilder setStudentId(int studentId);
    CourseStudents build();
}

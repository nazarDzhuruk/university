package com.foxminded.model.course.students;

public class CourseStudentBuilderImpl implements CourseStudentsBuilder{
    private int courseId;
    private int studentId;

    @Override
    public CourseStudentsBuilder setCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }

    @Override
    public CourseStudentsBuilder setStudentId(int studentId) {
        this.studentId = studentId;
        return this;
    }

    @Override
    public CourseStudents build() {
        CourseStudents courseStudents = new CourseStudents(courseId, studentId);
        return courseStudents;
    }
}

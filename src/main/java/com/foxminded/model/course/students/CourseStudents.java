package com.foxminded.model.course.students;

public class CourseStudents {
    private int courseId;
    private int studentId;

    public CourseStudents(){
        super();
    }

    public CourseStudents(int courseId, int studentId) {
        this();
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "CourseStudents{" +
                "courseId=" + courseId +
                ", studentId=" + studentId +
                '}';
    }
}

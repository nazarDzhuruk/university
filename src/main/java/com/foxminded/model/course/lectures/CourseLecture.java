package com.foxminded.model.course.lectures;

public class CourseLecture {
    private int courseId;
    private int lectureId;

    public CourseLecture(){
        super();
    }

    public CourseLecture(int courseId, int lectureId) {
        this();
        this.courseId = courseId;
        this.lectureId = lectureId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getLectureId() {
        return lectureId;
    }

    @Override
    public String toString() {
        return "CourseLecture{" +
                "courseId=" + courseId +
                ", lectureId=" + lectureId +
                '}';
    }
}

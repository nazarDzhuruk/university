package com.foxminded.model.course.lectures;

public class CourseLectureBuilderImpl implements CourseLectureBuilder{
    private int courseId;
    private int lectureId;


    @Override
    public CourseLectureBuilder setCourseId(int courseId) {
        this.courseId = courseId;
        return this;
    }

    @Override
    public CourseLectureBuilder setLectureId(int lectureId) {
        this.lectureId = lectureId;
        return this;
    }

    @Override
    public CourseLecture build() {
        CourseLecture courseLecture = new CourseLecture(courseId, lectureId);
        return courseLecture;
    }
}

package com.foxminded.model.course.lectures;

public interface CourseLectureBuilder {
    CourseLectureBuilder setCourseId(int courseId);
    CourseLectureBuilder setLectureId(int lectureId);
    CourseLecture build();
}

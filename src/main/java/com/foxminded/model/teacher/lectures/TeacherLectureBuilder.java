package com.foxminded.model.teacher.lectures;

public interface TeacherLectureBuilder {
    TeacherLectureBuilder setTeacher(int teacherId);
    TeacherLectureBuilder setLectures(int lecturesId);
    TeacherLecture build();
}

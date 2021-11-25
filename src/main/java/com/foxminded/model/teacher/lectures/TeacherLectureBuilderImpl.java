package com.foxminded.model.teacher.lectures;

public class TeacherLectureBuilderImpl implements TeacherLectureBuilder{
    private int teacherId;
    private int lecturesId;


    @Override
    public TeacherLectureBuilder setTeacher(int teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    @Override
    public TeacherLectureBuilder setLectures(int lecturesId) {
        this.lecturesId = lecturesId;
        return this;
    }

    @Override
    public TeacherLecture build() {
        TeacherLecture teacherLecture = new TeacherLecture(teacherId, lecturesId);
        return teacherLecture;
    }
}

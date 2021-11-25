package com.foxminded.model.teacher.lectures;

public class TeacherLecture {
    private int teacherId;
    private int lecturesId;

    public TeacherLecture() {
        super();
    }

    public TeacherLecture(int teacherId, int lecturesId) {
        this();
        this.teacherId = teacherId;
        this.lecturesId = lecturesId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getLecturesId() {
        return lecturesId;
    }

    @Override
    public String toString() {
        return "TeacherLecture{" +
                "teacherId=" + teacherId +
                ", lecturesId=" + lecturesId +
                '}';
    }
}

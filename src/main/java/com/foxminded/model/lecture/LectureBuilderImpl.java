package com.foxminded.model.lecture;

import java.util.Date;

public class LectureBuilderImpl implements LectureBuilder{
    private String lectureName;
    private Date lectureStartTime;
    private int id;

    @Override
    public LectureBuilder setLectureName(String lectureName) {
        this.lectureName = lectureName;
        return this;
    }

    @Override
    public LectureBuilder setLectureStartTime(Date startTime) {
        this.lectureStartTime = startTime;
        return this;
    }

    @Override
    public LectureBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Lecture build() {
        Lecture lecture = new Lecture(id, lectureName, lectureStartTime);
        return lecture;
    }
}

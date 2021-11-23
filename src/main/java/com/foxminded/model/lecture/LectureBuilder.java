package com.foxminded.model.lecture;

import java.util.Date;

public interface LectureBuilder {
    LectureBuilder setLectureName(String lectureName);
    LectureBuilder setLectureStartTime(Date startTime);
    LectureBuilder setId(int id);
    Lecture build();
}

package com.foxminded.model.teacher;

import com.foxminded.model.lecture.Lecture;

public interface TeacherBuilder {
    TeacherBuilder setName(String name);
    TeacherBuilder setSurname(String surname);
    TeacherBuilder setLecture(Lecture lecture);
    TeacherBuilder setId(int id);
    Teacher build();
}

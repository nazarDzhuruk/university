package com.foxminded.model.teacher;

import com.foxminded.model.lecture.Lecture;

import java.util.List;

public interface TeacherBuilder {
    TeacherBuilder setName(String name);
    TeacherBuilder setSurname(String surname);
    TeacherBuilder setLecture(List<Lecture> lecture);
    TeacherBuilder setId(int id);
    Teacher build();
}

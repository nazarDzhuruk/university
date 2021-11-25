package com.foxminded.model.student;

import com.foxminded.model.lecture.Lecture;

import java.util.List;

public interface StudentBuilder {
    StudentBuilder setName(String name);
    StudentBuilder setSurname(String surname);
    StudentBuilder setId(int id);
    StudentBuilder setCourse(List<Lecture> lectures);
    Student build();
}

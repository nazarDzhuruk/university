package com.foxminded.model.student;

import com.foxminded.model.course.Course;

public interface StudentBuilder {
    StudentBuilder setName(String name);
    StudentBuilder setSurname(String surname);
    StudentBuilder setId(int id);
    Student build();
}

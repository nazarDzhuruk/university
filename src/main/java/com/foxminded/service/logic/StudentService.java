package com.foxminded.service.logic;

import com.foxminded.model.student.Student;

public interface StudentService {
    void addStudent(Student student);

    void removeStudent(int id);

    Student findStudent(int id);

}

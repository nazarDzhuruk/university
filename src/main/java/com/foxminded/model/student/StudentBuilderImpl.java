package com.foxminded.model.student;

import com.foxminded.model.course.Course;

public class StudentBuilderImpl implements StudentBuilder{
    private String name;
    private String surname;
    private int id;
    private Course course;

    public StudentBuilderImpl(){
        super();
    }
    @Override
    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public StudentBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public StudentBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public StudentBuilder setCourse(Course course) {
        this.course = course;
        return this;
    }

    @Override
    public Student build() {
        Student student = new Student(id, name, surname, course);
        return student;
    }
}

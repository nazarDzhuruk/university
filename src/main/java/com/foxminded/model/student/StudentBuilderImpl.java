package com.foxminded.model.student;

import com.foxminded.model.lecture.Lecture;

import java.util.ArrayList;
import java.util.List;

public class StudentBuilderImpl implements StudentBuilder{
    private String name;
    private String surname;
    private int id;
    private List<Lecture> lectures = new ArrayList<>();

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
    public StudentBuilder setCourse(List<Lecture> lectures) {
        this.lectures = lectures;
        return this;
    }

    @Override
    public Student build() {
        Student student = new Student(id, name, surname, lectures);
        return student;
    }
}

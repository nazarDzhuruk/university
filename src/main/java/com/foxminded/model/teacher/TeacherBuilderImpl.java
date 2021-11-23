package com.foxminded.model.teacher;

import com.foxminded.model.lecture.Lecture;

public class TeacherBuilderImpl implements TeacherBuilder {
    private String name;
    private String surname;
    private Lecture lecture;
    private int id;

    public TeacherBuilderImpl(){
        super();
    }

    @Override
    public TeacherBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public TeacherBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public TeacherBuilder setLecture(Lecture lecture) {
        this.lecture = lecture;
        return this;
    }

    @Override
    public TeacherBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public Teacher build() {
        Teacher teacher = new Teacher(id, name, surname, lecture);
        return teacher;
    }
}

package com.foxminded.model.teacher;

import com.foxminded.model.lecture.Lecture;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Teacher {
    @Id
    private int ID;
    private String name;
    private String surname;

    @OneToOne
    private Lecture lecture;

    public Teacher(){
        super();
    }

    public Teacher(int id, String name, String surname, Lecture lecture) {
        this();
        this.name = name;
        this.surname = surname;
        this.ID = id;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lecture='" + lecture + '\'' +
                '}';
    }
}

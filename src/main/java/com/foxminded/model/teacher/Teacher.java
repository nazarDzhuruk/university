package com.foxminded.model.teacher;

import com.foxminded.model.lecture.Lecture;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id
    private int ID;
    private String name;
    private String surname;

    @OneToMany
    private List<Lecture> lecture = new ArrayList<>();

    public Teacher(){
        super();
    }

    public Teacher(int id, String name, String surname, List<Lecture> lecture) {
        this();
        this.name = name;
        this.surname = surname;
        this.ID = id;
        this.lecture = lecture;
    }

    public List<Lecture> getLecture() {
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

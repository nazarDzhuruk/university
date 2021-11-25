package com.foxminded.model.student;

import com.foxminded.model.lecture.Lecture;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private int ID;
    private String name;
    private String surname;
    @OneToMany
    private List<Lecture> lectures = new ArrayList<>();


    public Student(){
        super();
    }

    public Student(int id, String name, String surname, List<Lecture> lectures) {
        this();
        this.name = name;
        this.surname = surname;
        this.ID = id;
        this.lectures = lectures;
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

    public List<Lecture> getLectures() {
        return lectures;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lectures=" + lectures +
                '}';
    }
}

package com.foxminded.model.student;

import com.foxminded.model.course.Course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {
    @Id
    private int ID;

    private String name;
    private String surname;

    @OneToOne
    private Course course;

    public Student(){
        super();
    }

    public Student(int id, String name, String surname, Course course) {
        this();
        this.name = name;
        this.surname = surname;
        this.ID = id;
        this.course = course;
    }

    public Course getCourse() {
        return course;
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
        return "Student{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

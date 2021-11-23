package com.foxminded.model.course;

import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    private int courseID;
    private String courseName;

    @OneToMany (cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lecture> lecture = new HashSet<>();

    public Course(){
        super();
    }

    public Course(String courseName, int courseID, Set<Student> students, Set<Lecture> lecture) {
        this();
        this.courseName = courseName;
        this.students = students;
        this.courseID = courseID;
        this.lecture = lecture;
    }

    public int getCourseID() {
        return courseID;
    }

    public Set<Lecture> getLecture() {
        return lecture;
    }

    public String getCourseName() {
        return courseName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", students=" + students +
                ", lecture=" + lecture +
                '}';
    }
}

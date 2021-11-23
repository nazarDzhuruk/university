package com.foxminded.model.lecture;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Lecture {
    @Id
    private int ID;
    private String lectureName;
    private Date startTime;

    public Lecture() {
        super();
    }

    public Lecture(int ID, String lectureName, Date startTime) {
        this();
        this.lectureName = lectureName;
        this.startTime = startTime;
        this.ID = ID;
    }

    public String getLectureName() {
        return lectureName;
    }


    public Date getStartTime() {
        return startTime;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                ", lectureName='" + lectureName + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}

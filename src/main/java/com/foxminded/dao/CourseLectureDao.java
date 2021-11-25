package com.foxminded.dao;

import com.foxminded.model.course.lectures.CourseLecture;

import java.util.List;

public interface CourseLectureDao {
    void addLectures(int lectureId, int courseId);
    List<CourseLecture> index();
}

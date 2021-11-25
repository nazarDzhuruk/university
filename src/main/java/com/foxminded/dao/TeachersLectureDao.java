package com.foxminded.dao;

import com.foxminded.model.teacher.lectures.TeacherLecture;

import java.util.List;

public interface TeachersLectureDao {
    void assignLecture(int teacherId, int lectureId);
    List<TeacherLecture> index();

}

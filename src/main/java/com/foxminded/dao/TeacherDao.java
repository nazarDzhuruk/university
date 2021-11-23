package com.foxminded.dao;

import com.foxminded.model.teacher.Teacher;

public interface TeacherDao extends SimpleDao<Teacher> {
    void addLecture(int teacherId, int lectureId);

}

package com.foxminded.service.logic;

import com.foxminded.model.teacher.Teacher;

import java.util.Set;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    void removeTeacher(int id);

    void addLecture(int teacherId, int lectureId);

    Set<Teacher> teacherIndex();
}

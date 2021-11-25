package com.foxminded.service;

import com.foxminded.dao.LectureDao;
import com.foxminded.dao.TeacherDao;
import com.foxminded.dao.TeachersLectureDao;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.model.teacher.lectures.TeacherLecture;
import com.foxminded.service.logic.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TeacherServiceImpl implements TeacherService {
    private static Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final TeacherDao teacherDao;
    private final LectureDao lectureDao;
    private final TeachersLectureDao teachersLectureDao;


    public TeacherServiceImpl(TeacherDao teacherDao, LectureDao lectureDao, TeachersLectureDao teachersLectureDao) {
        this.teacherDao = teacherDao;
        this.lectureDao = lectureDao;
        this.teachersLectureDao = teachersLectureDao;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        logger.debug("Start adding teacher: " + teacher.getID());
        if (teacher == null) {
            String error = "Cannot create, please enter teacher info";
            logger.warn(error);
        }
        try {
            teacherDao.create(teacher);
            logger.info("Teacher has been added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTeacher(int id) {
        logger.debug("Start removing teacher: ");
        try {
            teacherDao.delete(id);
            logger.info("Teacher has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLecture(int teacherId, int lectureId) {
        logger.debug("Adding teacher lecture");
        try {
            Teacher teacher = teacherDao.index().stream()
                    .filter(t -> t.getID() == teacherId).findAny().orElse(null);
            Lecture lecture = lectureDao.index().stream()
                    .filter(l -> l.getID() == lectureId).findAny().orElse(null);
            if (teacher != null && lecture != null) {
                teachersLectureDao.assignLecture(teacher.getID(), lecture.getID());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Teacher findTeacher(int id) {

        List<TeacherLecture> teachersLecture = teachersLectureDao.index().stream()
                .filter(l -> l.getTeacherId() == id).collect(Collectors.toList());

        Teacher teacher = teacherDao.index().stream()
                .filter(t -> t.getID() == id).findAny().orElse(null);

        List<Lecture> lecture = lectureDao.index();

        List<Lecture> filtered = new ArrayList<>();

        for (Lecture forFilter : lecture) {
            for (TeacherLecture teacherLecture : teachersLecture) {
                if (forFilter.getID() == teacherLecture.getLecturesId()) {
                    filtered.add(forFilter);
                }
            }
        }
        teacher.getLecture().addAll(filtered);
        return teacher;
    }
}

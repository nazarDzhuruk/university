package com.foxminded.service;

import com.foxminded.dao.LectureDao;
import com.foxminded.dao.TeacherDao;
import com.foxminded.dao.TeachersLectureDao;
import com.foxminded.exception.UniversityDaoException;
import com.foxminded.exception.UniversityServiceException;
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
        logger.debug("Service start add teacher...");
        try {
            teacherDao.create(teacher);
        }catch (UniversityDaoException e){
            String msg = "Teacher has not been added";
            logger.warn(msg);
            throw new UniversityServiceException(msg);
        }
        logger.info("Teacher has been added");
    }

    @Override
    public void removeTeacher(int id) {
        logger.debug("Service start delete...");
        try {
            teacherDao.delete(id);
        }catch (UniversityDaoException e){
            String msg = "Teacher does not exist";
            logger.warn(msg);
            throw new UniversityServiceException(msg);
        }logger.info("Deleted");
    }

    @Override
    public void addLecture(int teacherId, int lectureId) {
        logger.debug("Adding teacher lecture");
        Teacher teacher = teacherDao.index().stream()
                .filter(t -> t.getID() == teacherId).findAny()
                .orElseThrow(() -> {
                            String exception = "Teacher not found";
                            logger.warn(exception);
                            return new UniversityServiceException(exception);
                        }
                );
        Lecture lecture = lectureDao.index().stream()
                .filter(l -> l.getID() == lectureId).findAny()
                .orElseThrow(() -> {
                    String exception = "Lecture not found";
                    logger.warn(exception);
                    return new UniversityServiceException(exception);
                });
        if (teacher != null && lecture != null) {
            teachersLectureDao.assignLecture(teacher.getID(), lecture.getID());
            logger.info("Assigned successfully!");
        }
    }

    @Override
    public Teacher findTeacher(int id) {
        logger.debug("Service start find teacher...");

        Teacher teacher = teacherDao.index().stream()
                .filter(t -> t.getID() == id).findAny()
                .orElseThrow(() -> {
                    String exception = "Teacher not found";
                    logger.warn(exception);
                    return new UniversityServiceException(exception);
                });

        List<TeacherLecture> teachersLecture = teachersLectureDao.index().stream()
                .filter(l -> l.getTeacherId() == id).collect(Collectors.toList());

        List<Lecture> filtered = new ArrayList<>();
        for (Lecture forFilter : lectureDao.index()) {
            for (TeacherLecture teacherLecture : teachersLecture) {
                if (forFilter.getID() == teacherLecture.getLecturesId()) {
                    filtered.add(forFilter);
                }
            }
        }
        logger.info("Teacher has been found");
        teacher.getLecture().addAll(filtered);
        return teacher;
    }
}

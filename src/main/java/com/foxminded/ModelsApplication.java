package com.foxminded;

import com.foxminded.dao.*;
import com.foxminded.model.student.StudentBuilder;
import com.foxminded.model.student.StudentBuilderImpl;
import com.foxminded.service.UniversityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModelsApplication {

    private static StudentDao studentDao;
    private static TeacherDao teacherDao;
    private static CourseDao courseDao;
    private static LectureDao lectureDao;
    private static UniversityService university;


    public ModelsApplication(StudentDao studentDao, UniversityService universityService, TeacherDao teacherDao, CourseDao courseDao, LectureDao lectureDao) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.courseDao = courseDao;
        this.lectureDao = lectureDao;
        this.university = universityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ModelsApplication.class, args);

        StudentBuilder studentBuilder = new StudentBuilderImpl();

        university.removeStudent(12211);



    }
}

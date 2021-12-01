package com.foxminded;

import com.foxminded.dao.*;
import com.foxminded.model.course.Course;
import com.foxminded.model.course.CourseBuilderImpl;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.lecture.LectureBuilderImpl;
import com.foxminded.model.student.Student;
import com.foxminded.model.student.StudentBuilder;
import com.foxminded.model.student.StudentBuilderImpl;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.model.teacher.TeacherBuilderImpl;
import com.foxminded.service.logic.CourseService;
import com.foxminded.service.logic.StudentService;
import com.foxminded.service.logic.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class ModelsApplication {


    private static StudentDao studentDao;
    private static TeacherDao teacherDao;
    private static CourseDao courseDao;
    private static LectureDao lectureDao;
    private static TeacherService teacherService;
    private static StudentService studentService;
    private static CourseService courseService;


    public ModelsApplication(StudentService studentService, TeacherService teacherService,
                             CourseService courseService, LectureDao lectureDao, CourseDao courseDao) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.courseDao = courseDao;
        this.lectureDao = lectureDao;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public static void main(String[] args) throws IOException, SQLException {
        SpringApplication.run(ModelsApplication.class, args);

        StudentBuilder studentBuilder = new StudentBuilderImpl();
        Teacher teacher = new TeacherBuilderImpl().setId(2134).setName("Test").setSurname("Teast").build();
        Lecture lecture = new LectureBuilderImpl().setId(123).setLectureName("Test").build();
        Student student = new StudentBuilderImpl().setId(3221).setName("Test2").setSurname("Test2").build();
        Course course = new CourseBuilderImpl().setCourseID(222).setCourseName("test").build();


        System.out.println();



    }
}

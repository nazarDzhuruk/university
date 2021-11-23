package com.foxminded.source.hibernate;

import com.foxminded.model.course.Course;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.model.student.Student;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.source.StartConnection;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernatePostgresConnection implements StartConnection {
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                configuration.configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(Lecture.class);
                configuration.addAnnotatedClass(Course.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    @Override
    public SessionFactory openSession() {
        return getSessionFactory();
    }
}

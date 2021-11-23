package com.foxminded.persistence.hibernate;

import com.foxminded.dao.CourseDao;
import com.foxminded.model.course.Course;
import com.foxminded.source.StartConnection;
import com.foxminded.source.hibernate.HibernatePostgresConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateCourseDao implements CourseDao {

    private Transaction transaction;

    private Session currentSession() {
        StartConnection startConnection = new HibernatePostgresConnection();
        return startConnection.openSession().getCurrentSession();
    }

    @Override
    public void create(Course course) {
        if (transaction == null) {
            try {
                transaction = currentSession().beginTransaction();
                currentSession().save(course);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public Optional<Course> read(int id) {
        transaction = currentSession().beginTransaction();
        Course course = currentSession().get(Course.class, id);
        transaction.commit();
        currentSession().close();
        return Optional.ofNullable(course);
    }

    @Override
    public void delete(int id) {
        if (transaction == null) {
            try {
                transaction = currentSession().beginTransaction();
                currentSession().delete(id);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public void update(Course course) {
        if (transaction == null) {
            try {
                transaction = currentSession().beginTransaction();
                currentSession().update(course);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public List<Course> index() {
        return null;
    }

}

package com.foxminded.persistence.hibernate;

import com.foxminded.dao.StudentDao;
import com.foxminded.model.student.Student;
import com.foxminded.source.StartConnection;
import com.foxminded.source.hibernate.HibernatePostgresConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateStudentDao implements StudentDao {
    private Transaction transaction;


    private Session currentSession(){
        StartConnection startConnection = new HibernatePostgresConnection();
        return startConnection.openSession().getCurrentSession();
    }


    @Override
    public void create(Student student) {
        if (transaction == null) {
            try {
                transaction = currentSession().beginTransaction();
                currentSession().save(student);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public Optional<Student> read(int id) {
        transaction = currentSession().beginTransaction();
        Student student = currentSession().get(Student.class, id);
        currentSession().close();
        return Optional.ofNullable(student);
    }

    @Override
    public void delete(int id) {
        if(transaction == null){
            try {
                transaction = currentSession().beginTransaction();
                currentSession().delete(id);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public void update(Student student) {
        if (transaction == null) {
            try {
                transaction = currentSession().beginTransaction();
                currentSession().update(student);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public List<Student> index() {
        return null;
    }
}

package com.foxminded.persistence.hibernate;

import com.foxminded.dao.TeacherDao;
import com.foxminded.model.teacher.Teacher;
import com.foxminded.source.StartConnection;
import com.foxminded.source.hibernate.HibernatePostgresConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateTeacherDao implements TeacherDao {
    Transaction transaction;

    private Session currentSession(){
        StartConnection startConnection = new HibernatePostgresConnection();
        return startConnection.openSession().getCurrentSession();
    }


    @Override
    public void create(Teacher teacher) {
        if(transaction == null){
            try{
                transaction = currentSession().beginTransaction();
                currentSession().save(teacher);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Teacher> read(int id) {
        transaction = currentSession().beginTransaction();
        Teacher teacher = currentSession().get(Teacher.class, id);
        currentSession().close();
        return Optional.ofNullable(teacher);
    }

    @Override
    public void delete(int id) {
        if(transaction == null){
            try{
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
    public void update(Teacher teacher) {
        if(transaction == null){
            try{
                transaction = currentSession().beginTransaction();
                currentSession().update(teacher);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public List<Teacher> index() {
        return null;
    }
}

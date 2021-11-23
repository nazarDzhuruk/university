package com.foxminded.persistence.hibernate;

import com.foxminded.dao.LectureDao;
import com.foxminded.model.lecture.Lecture;
import com.foxminded.source.StartConnection;
import com.foxminded.source.hibernate.HibernatePostgresConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateLectureDao implements LectureDao {
    private Transaction transaction;

    private Session currentSession(){
        StartConnection startConnection = new HibernatePostgresConnection();
        return startConnection.openSession().getCurrentSession();
    }

    @Override
    public void create(Lecture lecture) {
        if(transaction == null){
            try{
                transaction = currentSession().beginTransaction();
                currentSession().save(lecture);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
            currentSession().close();
        }
    }

    @Override
    public Optional<Lecture> read(int id) {
        transaction = currentSession().beginTransaction();
        Lecture lecture = currentSession().get(Lecture.class, id);
        currentSession().close();
        return Optional.ofNullable(lecture);
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
    public void update(Lecture lecture) {
        if(transaction == null){
            try{
                transaction = currentSession().beginTransaction();
                currentSession().update(lecture);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }
            currentSession().close();
        }

    }

    @Override
    public List<Lecture> index() {
        return null;
    }
}

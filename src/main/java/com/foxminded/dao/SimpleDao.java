package com.foxminded.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimpleDao<E> {

    void create(E e);

    Optional<E> read(int id);

    void delete(int id);

    void update(E e);

    List<E> index();
}

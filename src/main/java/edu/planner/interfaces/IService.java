package edu.planner.interfaces;

public interface IService<T, D> {

    T insert(D d);

    T update(D d);

    Boolean delete(Long id);
}
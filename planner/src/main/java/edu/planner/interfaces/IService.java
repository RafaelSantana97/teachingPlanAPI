package edu.planner.interfaces;

public interface IService<T> {

	public T insert(T t);

	public T update(T t);

	public Boolean delete(int id);
}
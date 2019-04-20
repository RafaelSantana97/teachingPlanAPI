package edu.planner.interfaces;

public interface IService<T, D> {

	public T insert(D d);

	public T update(D d);

	public Boolean delete(int id);
}
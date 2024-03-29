package db.dao;

import java.util.List;

public interface IBaseDAO<T> {
    List<T> getAllEntities();

    T getEntityById(int id);

    void updateEntity(T entity);

    void createEntity(T entity);

    void removeEntity(int id);
}

package isel.sisinf.model.mappers;

public interface IDataMapper<T>
{
    void create(T entity);
    void update(T entity);
    void delete(int key);
}



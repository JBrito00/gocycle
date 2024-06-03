package isel.sisinf.model.repo;

public interface IDataMapper<T>
{
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}



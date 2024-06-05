package isel.sisinf.jpa.genericInterfaces;

public interface IDataMapper<T>
{
    void create(T entity);
    void update(T entity);
    void delete(T entity);
}



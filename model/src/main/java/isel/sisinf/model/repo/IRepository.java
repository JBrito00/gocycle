package isel.sisinf.model.repo;

public interface IRepository<T,TCol,TK> {
    T findByKey(TK key);
    TCol find(String jpql, Object... params);
    void add(T t);
    void delete(TK key);
}


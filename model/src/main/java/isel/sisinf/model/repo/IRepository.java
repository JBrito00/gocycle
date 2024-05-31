package isel.sisinf.model.repo;

import java.util.List;

public interface IRepository <T, TCol, TK>{
    T findById(TK id);
    TCol find();
    void add(TCol col);
    void delete(TCol col);
    void save(TCol col);
    List<TCol> findAll();
}

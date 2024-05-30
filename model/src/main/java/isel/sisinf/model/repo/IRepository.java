package isel.sisinf.model.repo;

public interface IRepository <T, TCol, TK>{
    T findById(TK id);
    TCol find();
}

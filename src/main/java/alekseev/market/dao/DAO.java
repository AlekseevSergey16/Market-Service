package alekseev.market.dao;

import java.util.List;
import java.util.Optional;

public interface DAO <T> {
    void save(T t);
    T findById(int id);
    <S extends T> List<S> findAll();
    void updateById(int id, T t);
    void deleteById(int id);
}

package alekseev.market.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T> {
    void save(T t) throws SQLException;
    T findById(int id);
    <S extends T> List<S> findAll();
    void updateById(int id, T t) throws SQLException;
    void deleteById(int id) throws SQLException;
}

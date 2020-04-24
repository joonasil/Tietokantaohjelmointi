package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {

    int insert(T entity);

    List<T> selectAll();

    Optional<T> selectById(K key);

    int deleteById(K key);

    int updateById(K key, T entity);
}
package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, G> {

    int insert(T entity);

    List<T> selectAll();

    Optional<T> selectById(G id);

    int deleteById(G id);

    int updateById(G id, T entity);
}
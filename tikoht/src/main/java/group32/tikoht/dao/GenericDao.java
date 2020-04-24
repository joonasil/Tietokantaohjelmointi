package group32.tikoht.dao;

import group32.tikoht.model.Asiakas;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, G> {

    int insert(T entity);

    List<T> selectAll();

    Optional<T> selectById(G entityID);

    int deleteById(Integer entityID);

    int updateById(Integer ID, T entity);
}
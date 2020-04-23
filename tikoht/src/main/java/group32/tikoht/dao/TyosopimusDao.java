package group32.tikoht.dao;

import group32.tikoht.model.Tyosopimus;

import java.util.List;
import java.util.Optional;

public interface TyosopimusDao {
    
    int insertTyosopimus(Tyosopimus sopimus);

    List<Tyosopimus> selectAllTyosopimus();

    Optional<Tyosopimus> selectTyosopimusById(Integer sopimusID);

    int deleteTyosopimusById(Integer sopimusID);

    int updateTyosopimusById(Integer sopimusID, Tyosopimus sopimus);
}
package group32.tikoht.dao;

import group32.tikoht.model.Tyokohde;

import java.util.List;
import java.util.Optional;

public interface TyokohdeDao {
    
    int insertTyokohde(Tyokohde kohde);

    List<Tyokohde> selectAllTyokohde();

    Optional<Tyokohde> selectTyokohdeById(Integer kohdeID);

    int deleteTyokohdeById(Integer kohdeID);

    int updateTyokohdeById(Integer kohdeID, Tyokohde kohde);
}
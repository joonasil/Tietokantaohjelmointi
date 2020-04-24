package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TyokohdeDao;
import group32.tikoht.model.Tyokohde;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyokohdeService {

    private final TyokohdeDao tyokohdeDao;

    @Autowired
    public TyokohdeService(@Qualifier("tyokohdePSQL") TyokohdeDao tyokohdeDao) {
        this.tyokohdeDao = tyokohdeDao;
    }

    public int addTyokohde(Tyokohde kohde) {
        return tyokohdeDao.insert(kohde);
    }

    public List<Tyokohde> getAllTyokohde() {
        return tyokohdeDao.selectAll();
    }

    public Optional<Tyokohde> getTyokohdeById(Integer kohdeID) {
        return tyokohdeDao.selectById(kohdeID);
    }

    public int deleteTyokohde(Integer kohdeID) {
        return tyokohdeDao.deleteById(kohdeID);
    }

    public int updateTyokohde(Integer kohdeID, Tyokohde kohde) {
        return tyokohdeDao.updateById(kohdeID, kohde);
    }

}
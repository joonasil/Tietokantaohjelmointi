package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TyosopimusDao;
import group32.tikoht.model.Tyosopimus;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyosopimusService {

    private final TyosopimusDao tyosopimusDao;

    @Autowired
    public TyosopimusService(@Qualifier("tyosopimusPSQL") TyosopimusDao tyosopimusDao) {
        this.tyosopimusDao = tyosopimusDao;
    }

    public int addTyosopimus(Tyosopimus sopimus) {
        return tyosopimusDao.insert(sopimus);
    }

    public List<Tyosopimus> getAllTyosopimus() {
        return tyosopimusDao.selectAll();
    }

    public Optional<Tyosopimus> getTyosopimusById(Integer sopimusID) {
        return tyosopimusDao.selectById(sopimusID);
    }

    public int deleteTyosopimus(Integer sopimusID) {
        return tyosopimusDao.deleteById(sopimusID);
    }

    public int updateTyosopimus(Integer sopimusID, Tyosopimus sopimus) {
        return tyosopimusDao.updateById(sopimusID, sopimus);
    }

}
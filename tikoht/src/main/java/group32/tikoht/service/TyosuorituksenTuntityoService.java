package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TyosuorituksenTuntityoDao;
import group32.tikoht.model.TyosuorituksenTuntityo;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyosuorituksenTuntityoService {

    private final TyosuorituksenTuntityoDao tyosuorituksenTuntityoDao;

    @Autowired
    public TyosuorituksenTuntityoService(@Qualifier("tyotPSQL") TyosuorituksenTuntityoDao tyosuorituksenTuntityoDao) {
        this.tyosuorituksenTuntityoDao = tyosuorituksenTuntityoDao;
    }

    public int addTyosuorituksenTuntityo(TyosuorituksenTuntityo tyot) {
        return tyosuorituksenTuntityoDao.insert(tyot);
    }

    public List<TyosuorituksenTuntityo> getAllTyosuorituksenTuntityo() {
        return tyosuorituksenTuntityoDao.selectAll();
    }

    public Optional<TyosuorituksenTuntityo> getTyosuorituksenTuntityoById(Integer suoritusID) {
        return tyosuorituksenTuntityoDao.selectById(suoritusID);
    }

    public int deleteTyosuorituksenTuntityo(Integer suoritusID) {
        return tyosuorituksenTuntityoDao.deleteById(suoritusID);
    }

    public int updateTyosuorituksenTuntityo(Integer suoritusID, TyosuorituksenTuntityo tyot) {
        return tyosuorituksenTuntityoDao.updateById(suoritusID, tyot);
    }

}
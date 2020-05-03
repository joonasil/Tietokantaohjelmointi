package group32.tikoht.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TyosuoritusDao;
import group32.tikoht.model.TyosuorituksenTuntityo;
import group32.tikoht.model.Tyosuoritus;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyosuoritusService {

    private final TyosuoritusDao tyosuoritusDao;

    @Autowired
    public TyosuoritusService(@Qualifier("tyosuoritusPSQL") TyosuoritusDao tyosuoritusDao) {
        this.tyosuoritusDao = tyosuoritusDao;
    }

    public int addTyosuoritus(Tyosuoritus tyosuoritus) {
        return tyosuoritusDao.insert(tyosuoritus);
    }

    public List<Tyosuoritus> getAllTyosuoritus() {
        return tyosuoritusDao.selectAll();
    }

    public Optional<Tyosuoritus> getTyosuoritusById(Integer suoritusID) {
        return tyosuoritusDao.selectById(suoritusID);
    }

    public int deleteTyosuoritus(Integer suoritusID) {
        return tyosuoritusDao.deleteById(suoritusID);
    }

    public int updateTyosuoritus(Integer suoritusID, Tyosuoritus tyosuoritus) {
        return tyosuoritusDao.updateById(suoritusID, tyosuoritus);
    }

    public List<Tyosuoritus> getAllBySopimus(Integer id) {
        return tyosuoritusDao.selectAllBySopimus(id);
    }

}
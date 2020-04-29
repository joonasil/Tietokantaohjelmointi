package group32.tikoht.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TarvikeluetteloDao;
import group32.tikoht.dao.TuntityoDao;
import group32.tikoht.dao.TyosopimusDao;
import group32.tikoht.dao.TyosuorituksenTuntityoDao;
import group32.tikoht.dao.TyosuoritusDao;
import group32.tikoht.model.Tarvikeluettelo;
import group32.tikoht.model.Tuntityo;
import group32.tikoht.model.Tyosopimus;
import group32.tikoht.model.TyosuorituksenTuntityo;
import group32.tikoht.model.Tyosuoritus;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyosopimusService {

    private final TyosopimusDao tyosopimusDao;
    private final TyosuoritusDao suoritusDao;
    private final TyosuorituksenTuntityoDao tuntisuoritusDao;
    private final TarvikeluetteloDao tarvikeDao;
    private final TuntityoDao tuntityoDao;

    @Autowired
    public TyosopimusService(   @Qualifier("tyosopimusPSQL") TyosopimusDao tyosopimusDao,
                                @Qualifier("tyosuoritusPSQL") TyosuoritusDao suoritusDao,
                                @Qualifier("tyotPSQL") TyosuorituksenTuntityoDao tuntisuoritusDao,
                                @Qualifier("luetteloPSQL") TarvikeluetteloDao tarvikeDao,
                                @Qualifier("tuntityoPSQL") TuntityoDao tuntityoDao) {
        this.tyosopimusDao = tyosopimusDao;
        this.suoritusDao = suoritusDao;
        this.tuntisuoritusDao = tuntisuoritusDao;
        this.tarvikeDao = tarvikeDao;
        this.tuntityoDao = tuntityoDao;
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

    public double getContractWorkTotalSum(Integer sopimusID) {
        return tyosopimusDao.urakkaLaskuSumma(sopimusID);
    }

    // Palauttaa työsopimukseen liittyvien tuntitöiden kokonaissumman.
    public Double getHourWorkTotalSum(Integer sopimusID) {
        final List<Tyosuoritus> tyosuoritukset = suoritusDao.selectAllBySopimus(sopimusID);
        if (tyosuoritukset.isEmpty())
            return 0.0;
        final List<TyosuorituksenTuntityo> tyontiedot = new ArrayList<>();
        final List<Tarvikeluettelo> tarvikkeet = new ArrayList<>();
        for (Tyosuoritus suoritus : tyosuoritukset) {
            tyontiedot.addAll(tuntisuoritusDao.selectAllBySuoritusId(suoritus.getSuoritusid()));
            tarvikkeet.addAll(tarvikeDao.selectAllBySuoritusId(suoritus.getSuoritusid()));
        }
        Double sum = 0.0;
        for (TyosuorituksenTuntityo tyo : tyontiedot) {
            Tuntityo suoritus = tuntityoDao.selectById(tyo.getTyonTyyppi()).get();
            Double hinta = suoritus.getHinta();
            Integer alv = suoritus.getAlv();
            Double tunteja = tyo.getTuntiLkm();
            Double alennus = tyo.getAleProsentti();
            sum += hinta*tunteja*(1.0+(alv/100.0))*((100-alennus)/100);
        }
        return sum;
    }



}
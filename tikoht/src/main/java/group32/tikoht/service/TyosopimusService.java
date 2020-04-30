package group32.tikoht.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TarvikeDao;
import group32.tikoht.dao.TarvikeluetteloDao;
import group32.tikoht.dao.TuntityoDao;
import group32.tikoht.dao.TyosopimusDao;
import group32.tikoht.dao.TyosuorituksenTuntityoDao;
import group32.tikoht.dao.TyosuoritusDao;
import group32.tikoht.model.Tarvike;
import group32.tikoht.model.Tarvikeluettelo;
import group32.tikoht.model.Tuntityo;
import group32.tikoht.model.Tuntityolasku;
import group32.tikoht.model.Tyosopimus;
import group32.tikoht.model.TyosuorituksenTuntityo;
import group32.tikoht.model.Tyosuoritus;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TyosopimusService {

    private final TyosopimusDao tyosopimusDao;
    private final TyosuoritusDao suoritusDao;
    private final TyosuorituksenTuntityoDao tuntisuoritusDao;
    private final TarvikeluetteloDao tarvikeluetteloDao;
    private final TuntityoDao tuntityoDao;
    private final TarvikeDao tarvikeDao;

    @Autowired
    public TyosopimusService(   @Qualifier("tyosopimusPSQL") TyosopimusDao tyosopimusDao,
                                @Qualifier("tyosuoritusPSQL") TyosuoritusDao suoritusDao,
                                @Qualifier("tyotPSQL") TyosuorituksenTuntityoDao tuntisuoritusDao,
                                @Qualifier("luetteloPSQL") TarvikeluetteloDao tarvikeluetteloDao,
                                @Qualifier("tuntityoPSQL") TuntityoDao tuntityoDao,
                                @Qualifier("tarvikePSQL") TarvikeDao tarvikeDao) {
        this.tyosopimusDao = tyosopimusDao;
        this.suoritusDao = suoritusDao;
        this.tuntisuoritusDao = tuntisuoritusDao;
        this.tarvikeluetteloDao = tarvikeluetteloDao;
        this.tuntityoDao = tuntityoDao;
        this.tarvikeDao = tarvikeDao;
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
            tarvikkeet.addAll(tarvikeluetteloDao.selectAllBySuoritusId(suoritus.getSuoritusid()));
        }
        Double sum = 0.0;
        for (TyosuorituksenTuntityo tyo : tyontiedot) {
            Tuntityo suoritus = tuntityoDao.selectById(tyo.getTyonTyyppi()).get();
            Double hinta = suoritus.getHinta();
            Integer alv = suoritus.getAlv();
            Double tunteja = tyo.getTuntiLkm();
            Double alennus = tyo.getAleProsentti();
            sum += hinta*tunteja*(1.0+(alv/100.0)) - (hinta*tunteja*(alennus/100.0));
        }
        return sum;
    }

    public Tuntityolasku getHourInvoice(Integer sopimusId) {
        Tyosopimus sopimus = tyosopimusDao.selectById(sopimusId).get();
        Tuntityolasku o1 = tyosopimusDao.getContractDetails(sopimusId);
        if (sopimus.getTyyppi().equals("tunti")) {
            List<Tuntityo> tyotyypit = tuntityoDao.selectAllBySopimus(sopimusId);
            List<TyosuorituksenTuntityo> tyot = tuntisuoritusDao.selectAllBySopimusIdGrouped(sopimusId);
            List<Tarvikeluettelo> tarvikkeet = tarvikeluetteloDao.selectAllBySopimusIdGrouped(sopimusId);
            List<Tarvike> tarviketiedot = tarvikeDao.selectAllBySopimus(sopimusId);
            if (tyotyypit.isEmpty()) {
                tyotyypit.add(new Tuntityo(null, null, null));
            }
            if (tyot.isEmpty()) {
                tyot.add(new TyosuorituksenTuntityo(null, null, null, null));
            }
            if (tarvikkeet.isEmpty()) {
                tarvikkeet.add(new Tarvikeluettelo(null, null, null, null));
            }
            if (tarviketiedot.isEmpty()) {
                tarviketiedot.add(new Tarvike(null, null, null, null, null, null, null));
            }
            return new Tuntityolasku(o1.getAsiakas(), o1.getAsiakasosoite(), o1.getKohdeosoite(), tyotyypit, tyot, tarvikkeet, tarviketiedot, getHourWorkTotalSum(sopimusId));
        } else {
            List<Tuntityo> tyotyypit = new ArrayList<>();
            List<TyosuorituksenTuntityo> tyot = new ArrayList<>();
            List<Tarvikeluettelo> tarvikkeet = new ArrayList<>();
            List<Tarvike> tarviketiedot = new ArrayList<>();
            tyot.add(new TyosuorituksenTuntityo(null, "Urakan tyot", 1.0, 0.0));
            tarviketiedot.add(new Tarvike(99999, "Urakan tarvikkeet", sopimus.getTarvikkeidenhinta(), sopimus.getTarvikkeidenhinta(), "kpl", null, 24));
            tyotyypit.add(new Tuntityo("Urakan tyot", sopimus.getTyonHinta(), 24));
            tarvikkeet.add(new Tarvikeluettelo(null, 99999, 1, 0));
            return new Tuntityolasku(o1.getAsiakas(), o1.getAsiakasosoite(), o1.getKohdeosoite(), tyotyypit, tyot, tarvikkeet, tarviketiedot, sopimus.getSopimuksenSumma());
        }
        
    }

}
package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TuntityoDao;
import group32.tikoht.model.Tuntityo;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TuntityoService {

    private final TuntityoDao tuntityoDao;

    @Autowired
    public TuntityoService(@Qualifier("tuntityoPSQL") TuntityoDao tuntityoDao) {
        this.tuntityoDao = tuntityoDao;
    }

    public int addTuntityo(Tuntityo tuntityo) {
        return tuntityoDao.insert(tuntityo);
    }

    public List<Tuntityo> getAllTuntityo() {
        return tuntityoDao.selectAll();
    }

    public Optional<Tuntityo> getTuntityoById(String tyonTyyppi) {
        return tuntityoDao.selectById(tyonTyyppi);
    }

    public int deleteTuntityo(String tyonTyyppi) {
        return tuntityoDao.deleteById(tyonTyyppi);
    }

    public int updateTuntityo(String tyonTyyppi, Tuntityo tuntityo) {
        return tuntityoDao.updateById(tyonTyyppi, tuntityo);
    }

}
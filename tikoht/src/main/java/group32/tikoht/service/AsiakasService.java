package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.AsiakasDao;
import group32.tikoht.model.Asiakas;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class AsiakasService {

    private final AsiakasDao asiakasDao;

    @Autowired
    public AsiakasService(@Qualifier("asiakasPSQL") AsiakasDao asiakasDao) {
        this.asiakasDao = asiakasDao;
    }

    public int addAsiakas(Asiakas asiakas) {
        return asiakasDao.insert(asiakas);
    }

    public List<Asiakas> getAllAsiakas() {
        return asiakasDao.selectAll();
    }

    public Optional<Asiakas> getAsiakasById(Integer asiakasID) {
        return asiakasDao.selectById(asiakasID);
    }

    public int deleteAsiakas(Integer asiakasID) {
        return asiakasDao.deleteById(asiakasID);
    }

    public int updateAsiakas(Integer asiakasID, Asiakas asiakas) {
        return asiakasDao.updateById(asiakasID, asiakas);
    }

}
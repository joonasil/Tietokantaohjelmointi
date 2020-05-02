package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.LaskuDao;
import group32.tikoht.model.Lasku;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class LaskuService {

    private final LaskuDao laskuDao;

    @Autowired
    public LaskuService(@Qualifier("laskuPSQL") LaskuDao laskuDao) {
        this.laskuDao = laskuDao;
    }

    public int addLasku(Lasku lasku) {
        return laskuDao.insert(lasku);
    }

    public List<Lasku> getAllLasku() {
        return laskuDao.selectAll();
    }

    public Optional<Lasku> getLaskuById(Integer laskuID) {
        return laskuDao.selectById(laskuID);
    }

    public int deleteLasku(Integer laskuID) {
        return laskuDao.deleteById(laskuID);
    }

    public int updateLasku(Integer laskuID, Lasku lasku) {
        return laskuDao.updateById(laskuID, lasku);
    }

    // Laskun omat
    public List<Lasku> getAllOverdue() { return laskuDao.selectAllOverdue();}

    public int generateOverdueInvoices() { return laskuDao.generateOverdueInvoices();}

}
package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TarvikeluetteloDao;
import group32.tikoht.model.Tarvikeluettelo;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TarvikeluetteloService {

    private final TarvikeluetteloDao luetteloDao;

    @Autowired
    public TarvikeluetteloService(@Qualifier("luetteloPSQL") TarvikeluetteloDao luetteloDao) {
        this.luetteloDao = luetteloDao;
    }

    public int addTarvikeluettelo(Tarvikeluettelo luettelo) {
        return luetteloDao.insert(luettelo);
    }

    public List<Tarvikeluettelo> getAllTarvikeluettelo() {
        return luetteloDao.selectAll();
    }

    public Optional<Tarvikeluettelo> getTarvikeluetteloById(Integer suoritusID) {
        return luetteloDao.selectById(suoritusID);
    }

    public int deleteTarvikeluettelo(Integer suoritusID) {
        return luetteloDao.deleteById(suoritusID);
    }

    public int updateTarvikeluettelo(Integer suoritusID, Tarvikeluettelo luettelo) {
        return luetteloDao.updateById(suoritusID, luettelo);
    }

    public List<Tarvikeluettelo> getAllBySuoritusId(Integer suoritusId) {
        return luetteloDao.selectAllBySuoritusId(suoritusId);
    }

}
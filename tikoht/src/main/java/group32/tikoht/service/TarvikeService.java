package group32.tikoht.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group32.tikoht.dao.TarvikeDao;
import group32.tikoht.model.Tarvike;

// Mahdollinen tarvittava serverilogiikka tähän
@Service
public class TarvikeService {

    private final TarvikeDao tarvikeDao;

    @Autowired
    public TarvikeService(@Qualifier("tarvikePSQL") TarvikeDao tarvikeDao) {
        this.tarvikeDao = tarvikeDao;
    }

    public int addTarvike(Tarvike tarvike) {
        return tarvikeDao.insert(tarvike);
    }

    public List<Tarvike> getAllTarvike() {
        return tarvikeDao.selectAll();
    }

    public Optional<Tarvike> getTarvikeById(Integer tarvikeID) {
        return tarvikeDao.selectById(tarvikeID);
    }

    public int deleteTarvike(Integer tarvikeID) {
        return tarvikeDao.deleteById(tarvikeID);
    }

    public int updateTarvike(Integer tarvikeID, Tarvike tarvike) {
        return tarvikeDao.updateById(tarvikeID, tarvike);
    }

}
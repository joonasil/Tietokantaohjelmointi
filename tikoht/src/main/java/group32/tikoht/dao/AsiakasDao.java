package group32.tikoht.dao;

import group32.tikoht.model.Asiakas;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface AsiakasDao {
    
    int insertAsiakas(Integer asiakasID, Asiakas asiakas);

    default int insertAsiakas(Asiakas asiakas) {
        Random rand = new Random();
        Integer asiakasID = rand.nextInt(50);
        return insertAsiakas(asiakasID, asiakas);
    }

    List<Asiakas> selectAllAsiakas();

    Optional<Asiakas> selectAsiakasById(Integer asiakasID);

    int deleteAsiakasById(Integer asiakasID);

    int updateAsiakasById(Integer asiakasID, Asiakas asiakas);
}
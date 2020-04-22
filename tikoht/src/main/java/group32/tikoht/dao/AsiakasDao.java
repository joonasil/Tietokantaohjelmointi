package group32.tikoht.dao;

import group32.tikoht.model.Asiakas;

import java.util.List;
import java.util.Optional;

public interface AsiakasDao {
    
    int insertAsiakas(Asiakas asiakas);

    List<Asiakas> selectAllAsiakas();

    Optional<Asiakas> selectAsiakasById(Integer asiakasID);

    int deleteAsiakasById(Integer asiakasID);

    int updateAsiakasById(Integer asiakasID, Asiakas asiakas);
}
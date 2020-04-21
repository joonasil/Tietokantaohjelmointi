package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Asiakas;

@Repository("postgres")
public class AsiakasDataAccessService implements AsiakasDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AsiakasDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAsiakas(Integer asiakasID, Asiakas asiakas) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Asiakas> selectAllAsiakas() {
        final String sql = "SELECT asiakasID, nimi, osoite FROM asiakas";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer asiakasID = rs.getInt("asiakasID");
            String nimi = rs.getString("nimi");
            String osoite = rs.getString("osoite");
            return new Asiakas(asiakasID, nimi, osoite);
        });
    }

    @Override
    public Optional<Asiakas> selectAsiakasById(Integer asiakasID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteAsiakasById(Integer asiakasID) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateAsiakasById(Integer asiakasID, Asiakas asiakas) {
        // TODO Auto-generated method stub
        return 0;
    }



}
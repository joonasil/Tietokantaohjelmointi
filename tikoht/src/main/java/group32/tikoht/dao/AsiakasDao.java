package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Asiakas;

@Repository("asiakasPSQL")
public class AsiakasDao implements GenericDao<Asiakas, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AsiakasDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Asiakas asiakas) {
        String nimi = asiakas.getNimi();
        String osoite = asiakas.getOsoite();
        final String sql =  "INSERT INTO asiakas(nimi, osoite) " +
                            "VALUES(?, ?)";
        return jdbcTemplate.update(sql, new Object[]{nimi, osoite});
    }

    @Override
    public List<Asiakas> selectAll() {
        final String sql =  "SELECT asiakasID, nimi, osoite " +
                            "FROM asiakas";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer asiakasID = rs.getInt("asiakasID");
            String nimi = rs.getString("nimi");
            String osoite = rs.getString("osoite");
            return new Asiakas(asiakasID, nimi, osoite);
        });
    }

    @Override
    public Optional<Asiakas> selectById(Integer id) {
        final String sql =  "SELECT asiakasID, nimi, osoite " +
                            "FROM asiakas " +
                            "WHERE asiakasID = ?";
        Asiakas asiakas = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer asiakasID = rs.getInt("asiakasID");
            String nimi = rs.getString("nimi");
            String osoite = rs.getString("osoite");
            return new Asiakas(asiakasID, nimi, osoite);
        });
        return Optional.ofNullable(asiakas);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM asiakas " +
                            "WHERE asiakasID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Asiakas asiakas) {
        String nimi = asiakas.getNimi();
        String osoite = asiakas.getOsoite();
        final String sql =  "UPDATE asiakas " +
                            "SET nimi = ?, osoite = ? " +
                            "WHERE asiakasID = ?";
        return jdbcTemplate.update(sql, new Object[]{nimi, osoite, id});
    }
    
}
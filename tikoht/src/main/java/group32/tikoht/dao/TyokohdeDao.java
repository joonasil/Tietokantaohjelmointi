package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tyokohde;

@Repository("tyokohdePSQL")
public class TyokohdeDao implements GenericDao<Tyokohde, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TyokohdeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tyokohde kohde) {
        Integer omistajaID = kohde.getOmistajaID();
        String tyyppi = kohde.getKohdetyyppi();
        String osoite = kohde.getOsoite();
        final String sql =  "INSERT INTO tyokohde(omistajaID, kohdetyyppi, osoite) " +
                            "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{omistajaID, tyyppi, osoite});
    }

    @Override
    public List<Tyokohde> selectAll() {
        final String sql =  "SELECT kohdeID, omistajaID, kohdetyyppi, osoite " +
                            "FROM tyokohde";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer kohdeID = rs.getInt("kohdeID");
            Integer omistajaID = rs.getInt("omistajaID");
            String tyyppi = rs.getString("kohdetyyppi");
            String osoite = rs.getString("osoite");
            return new Tyokohde(kohdeID, omistajaID, tyyppi, osoite);
        });
    }

    @Override
    public Optional<Tyokohde> selectById(Integer id) {
        final String sql =  "SELECT kohdeID, omistajaID, kohdetyyppi, osoite " +
                            "FROM tyokohde " +
                            "WHERE kohdeID = ?";
        Tyokohde kohde = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer kohdeID = rs.getInt("kohdeID");
            Integer omistajaID = rs.getInt("omistajaID");
            String tyyppi = rs.getString("kohdetyyppi");
            String osoite = rs.getString("osoite");
            return new Tyokohde(kohdeID, omistajaID, tyyppi, osoite);
        });
        return Optional.ofNullable(kohde);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyokohde " +
                            "WHERE kohdeID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyokohde kohde) {
        Integer omistajaID = kohde.getOmistajaID();
        String tyyppi = kohde.getKohdetyyppi();
        String osoite = kohde.getOsoite();
        final String sql =  "UPDATE tyokohde " +
                            "SET omistajaID = ?, kohdetyyppi = ?, osoite = ? " +
                            "WHERE kohdeID = ?";
        return jdbcTemplate.update(sql, new Object[]{omistajaID, tyyppi, osoite, id});
    }



}
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
        Integer omistajaid = kohde.getOmistajaid();
        String tyyppi = kohde.getKohdetyyppi();
        String osoite = kohde.getOsoite();
        final String sql =  "INSERT INTO tyokohde(omistajaid, kohdetyyppi, osoite) " +
                            "VALUES(?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{omistajaid, tyyppi, osoite});
    }

    @Override
    public List<Tyokohde> selectAll() {
        final String sql =  "SELECT kohdeid, omistajaid, kohdetyyppi, osoite " +
                            "FROM tyokohde";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer kohdeid = rs.getInt("kohdeid");
            Integer omistajaid = rs.getInt("omistajaid");
            String tyyppi = rs.getString("kohdetyyppi");
            String osoite = rs.getString("osoite");
            return new Tyokohde(kohdeid, omistajaid, tyyppi, osoite);
        });
    }

    @Override
    public Optional<Tyokohde> selectById(Integer id) {
        final String sql =  "SELECT kohdeid, omistajaid, kohdetyyppi, osoite " +
                            "FROM tyokohde " +
                            "WHERE kohdeid = ?";
        Tyokohde kohde = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer kohdeid = rs.getInt("kohdeid");
            Integer omistajaid = rs.getInt("omistajaid");
            String tyyppi = rs.getString("kohdetyyppi");
            String osoite = rs.getString("osoite");
            return new Tyokohde(kohdeid, omistajaid, tyyppi, osoite);
        });
        return Optional.ofNullable(kohde);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyokohde " +
                            "WHERE kohdeid = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyokohde kohde) {
        Integer omistajaid = kohde.getOmistajaid();
        String tyyppi = kohde.getKohdetyyppi();
        String osoite = kohde.getOsoite();
        final String sql =  "UPDATE tyokohde " +
                            "SET omistajaid = ?, kohdetyyppi = ?, osoite = ? " +
                            "WHERE kohdeid = ?";
        return jdbcTemplate.update(sql, new Object[]{omistajaid, tyyppi, osoite, id});
    }



}
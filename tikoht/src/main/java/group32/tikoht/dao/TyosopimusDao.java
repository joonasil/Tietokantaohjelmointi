package group32.tikoht.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tyosopimus;

@Repository("tyosopimusPSQL")
public class TyosopimusDao implements GenericDao<Tyosopimus, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TyosopimusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tyosopimus sopimus) {
        Integer kohdeID = sopimus.getKohdeID();
        String tyyppi = sopimus.getTyyppi();
        Double tyonHinta = sopimus.getTyonHinta();
        Double tarvikkeidenHinta = sopimus.getTarvikkeidenHinta();
        Integer osamaksu = sopimus.getOsamaksu();
        LocalDate pvm = sopimus.getPvm();
        String sopimuksenTila = sopimus.getSopimuksenTila();
        String selite = sopimus.getSelite();
        final String sql =  "INSERT INTO tyosopimus(kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite) " +
                            "VALUES(?, ?::sopimuslaji, ?, ?, ?, ?, ?::sopimusTila, ?)";
        return jdbcTemplate.update(sql, new Object[]{kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite});
    }

    @Override
    public List<Tyosopimus> selectAll() {
        final String sql =  "SELECT sopimusID, kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite " +
                            "FROM tyosopimus";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer sopimusID = rs.getInt("sopimusID");
            Integer kohdeID = rs.getInt("kohdeID");
            String tyyppi = rs.getString("tyyppi");
            Double tyonHinta = rs.getDouble("tyonHinta");
            Double tarvikkeidenHinta = rs.getDouble("tarvikkeidenHinta");
            Integer osamaksu = rs.getInt("osamaksu");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            String sopimuksenTila = rs.getString("sopimuksenTila");
            String selite = rs.getString("selite");
            return new Tyosopimus(sopimusID, kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite);
        });
    }

    @Override
    public Optional<Tyosopimus> selectById(Integer id) {
        final String sql =  "SELECT sopimusID, kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite " +
                            "FROM tyosopimus " +
                            "WHERE sopimusID = ?";
        Tyosopimus sopimus = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer sopimusID = rs.getInt("sopimusID");
            Integer kohdeID = rs.getInt("kohdeID");
            String tyyppi = rs.getString("tyyppi");
            Double tyonHinta = rs.getDouble("tyonHinta");
            Double tarvikkeidenHinta = rs.getDouble("tarvikkeidenHinta");
            Integer osamaksu = rs.getInt("osamaksu");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            String sopimuksenTila = rs.getString("sopimuksenTila");
            String selite = rs.getString("selite");
            return new Tyosopimus(sopimusID, kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite);
        });
        return Optional.ofNullable(sopimus);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyosopimus " +
                            "WHERE sopimusID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyosopimus sopimus) {
        Integer kohdeID = sopimus.getKohdeID();
        String tyyppi = sopimus.getTyyppi();
        Double tyonHinta = sopimus.getTyonHinta();
        Double tarvikkeidenHinta = sopimus.getTarvikkeidenHinta();
        Integer osamaksu = sopimus.getOsamaksu();
        LocalDate pvm = sopimus.getPvm();
        String sopimuksenTila = sopimus.getSopimuksenTila();
        String selite = sopimus.getSelite();
        final String sql =  "UPDATE tyosopimus " +
                            "SET kohdeID = ?, tyyppi = ?::sopimuslaji, tyonHinta = ?, tarvikkeidenHinta = ?, osamaksu = ?, pvm = ?, sopimuksenTila = ?::sopimusTila, selite = ? " +
                            "WHERE sopimusID = ?";
        return jdbcTemplate.update(sql, new Object[]{kohdeID, tyyppi, tyonHinta, tarvikkeidenHinta, osamaksu, pvm, sopimuksenTila, selite, id});
    }
}
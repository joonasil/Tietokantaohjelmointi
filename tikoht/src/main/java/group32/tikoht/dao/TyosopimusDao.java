package group32.tikoht.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tuntityolasku;
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
        Integer kohdeid = sopimus.getKohdeid();
        String tyyppi = sopimus.getTyyppi();
        Double tyonhinta = sopimus.getTyonHinta();
        Double tarvikkeidenhinta = sopimus.getTarvikkeidenhinta();
        Integer osamaksu = sopimus.getOsamaksu();
        LocalDate pvm = sopimus.getPvm();
        String sopimuksentila = sopimus.getSopimuksentila();
        String selite = sopimus.getSelite();
        final String sql =  "INSERT INTO tyosopimus(kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite) " +
                            "VALUES(?, ?::sopimuslaji, ?, ?, ?, ?, ?::sopimusTila, ?)";
        return jdbcTemplate.update(sql, new Object[]{kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite});
    }

    @Override
    public List<Tyosopimus> selectAll() {
        final String sql =  "SELECT sopimusid, kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite " +
                            "FROM tyosopimus";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer sopimusid = rs.getInt("sopimusid");
            Integer kohdeid = rs.getInt("kohdeid");
            String tyyppi = rs.getString("tyyppi");
            Double tyonhinta = rs.getDouble("tyonhinta");
            Double tarvikkeidenhinta = rs.getDouble("tarvikkeidenhinta");
            Integer osamaksu = rs.getInt("osamaksu");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            String sopimuksentila = rs.getString("sopimuksentila");
            String selite = rs.getString("selite");
            return new Tyosopimus(sopimusid, kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite);
        });
    }

    @Override
    public Optional<Tyosopimus> selectById(Integer id) {
        final String sql =  "SELECT sopimusid, kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite " +
                            "FROM tyosopimus " +
                            "WHERE sopimusid = ?";
        Tyosopimus sopimus = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer sopimusid = rs.getInt("sopimusid");
            Integer kohdeid = rs.getInt("kohdeid");
            String tyyppi = rs.getString("tyyppi");
            Double tyonhinta = rs.getDouble("tyonhinta");
            Double tarvikkeidenhinta = rs.getDouble("tarvikkeidenhinta");
            Integer osamaksu = rs.getInt("osamaksu");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            String sopimuksentila = rs.getString("sopimuksentila");
            String selite = rs.getString("selite");
            return new Tyosopimus(sopimusid, kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite);
        });
        return Optional.ofNullable(sopimus);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyosopimus " +
                            "WHERE sopimusid = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyosopimus sopimus) {
        Integer kohdeid = sopimus.getKohdeid();
        String tyyppi = sopimus.getTyyppi();
        Double tyonhinta = sopimus.getTyonHinta();
        Double tarvikkeidenhinta = sopimus.getTarvikkeidenhinta();
        Integer osamaksu = sopimus.getOsamaksu();
        LocalDate pvm = sopimus.getPvm();
        String sopimuksentila = sopimus.getSopimuksentila();
        String selite = sopimus.getSelite();
        final String sql =  "UPDATE tyosopimus " +
                            "SET kohdeid = ?, tyyppi = ?::sopimuslaji, tyonhinta = ?, tarvikkeidenhinta = ?, osamaksu = ?, pvm = ?, sopimuksentila = ?::sopimusTila, selite = ? " +
                            "WHERE sopimusid = ?";
        return jdbcTemplate.update(sql, new Object[]{kohdeid, tyyppi, tyonhinta, tarvikkeidenhinta, osamaksu, pvm, sopimuksentila, selite, id});
    }

    public double urakkaLaskuSumma(Integer key) {
        Tyosopimus sopimus = selectById(key).get();
        return sopimus.getTyonHinta() + sopimus.getTarvikkeidenhinta() / sopimus.getOsamaksu();
    }

    public Tuntityolasku getHourInvoice(Integer sopimusId) {
        final String sql =  "SELECT nimi, asiakas.osoite AS aosoite, tyokohde.osoite AS tosoite, " +
                            "FROM asiakas, tyokohde, tyosopimus " +
                            "WHERE sopimusid = ? " +
                            "AND tyosopimus.kohdeid = tyokohde.kohdeid " +
                            "AND tyokohde.asiakasid = asiakas.asiakasid";
        Tuntityolasku osa1 = jdbcTemplate.queryForObject(sql, new Object[]{sopimusId}, (rs, i) -> {
            String asiakas = rs.getString("nimi");
            String asiakasosoite = rs.getString("aosoite");
            String kohdeosoite = rs.getString("tosoite");
            return new Tuntityolasku(asiakas, asiakasosoite, kohdeosoite, null, null, null, null, null);
        });
        return osa1;
    }

}
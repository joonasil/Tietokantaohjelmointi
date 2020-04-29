package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tuntityo;

@Repository("tuntityoPSQL")
public class TuntityoDao implements GenericDao<Tuntityo, String> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TuntityoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tuntityo tuntityo) {
        String tyonTyyppi = tuntityo.getTyonTyyppi();
        Double hinta = tuntityo.getHinta();
        Integer alv = tuntityo.getAlv();
        final String sql =  "INSERT INTO tuntityo " +
                            "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{tyonTyyppi, hinta, alv});
    }

    @Override
    public List<Tuntityo> selectAll() {
        final String sql =  "SELECT tyontyyppi, hinta, alv " +
                            "FROM tuntityo";
        return jdbcTemplate.query(sql, (rs, i) -> {
            String tyonTyyppi = rs.getString("tyontyyppi");
            Double hinta = rs.getDouble("hinta");
            Integer alv = rs.getInt("alv");
            return new Tuntityo(tyonTyyppi, hinta, alv);
        });
    }

    @Override
    public Optional<Tuntityo> selectById(String id) {
        final String sql =  "SELECT tyontyyppi, hinta, alv " +
                            "FROM tuntityo " +
                            "WHERE tyontyyppi = ?";
        Tuntityo tuntityo = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            String tyonTyyppi = rs.getString("tyontyyppi");
            Double hinta = rs.getDouble("hinta");
            Integer alv = rs.getInt("alv");
            return new Tuntityo(tyonTyyppi, hinta, alv);
        });
        return Optional.ofNullable(tuntityo);
    }

    @Override
    public int deleteById(String id) {
        final String sql =  "DELETE FROM tuntityo " +
                            "WHERE tyontyyppi = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(String id, Tuntityo tuntityo) {
        Double hinta = tuntityo.getHinta();
        Integer alv = tuntityo.getAlv();
        final String sql =  "UPDATE tuntityo " +
                            "SET hinta = ?, alv = ? " +
                            "WHERE tyontyyppi = ?";
        return jdbcTemplate.update(sql, new Object[]{hinta, alv, id});
    }

    public List<Tuntityo> selectAllBySopimus(Integer sopimusId) {
        final String sql =  "SELECT tyontyyppi, hinta, alv " +
                            "FROM tuntityo, tyosuorituksenTuntityo, tyosuoritus, tyosopimus" +
                            "WHERE tyosopimus.sopimusid = ?" +
                            "AND tyosopimus.sopimusid = tyosuoritus.sopimusid" +
                            "AND tyosuoritus.suoritusid = tyosuorituksenTuntityo.suoritusid" +
                            "AND tyosuorituksenTuntityo.tyontyyppi = tuntityo.tyontyyppi";
        return jdbcTemplate.query(sql, (rs, i) -> {
            String tyonTyyppi = rs.getString("tyontyyppi");
            Double hinta = rs.getDouble("hinta");
            Integer alv = rs.getInt("alv");
            return new Tuntityo(tyonTyyppi, hinta, alv);
        });
    }
    
}
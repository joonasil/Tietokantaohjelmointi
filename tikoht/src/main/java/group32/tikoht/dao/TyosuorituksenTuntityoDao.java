package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.TyosuorituksenTuntityo;

@Repository("tyotPSQL")
public class TyosuorituksenTuntityoDao implements GenericDao<TyosuorituksenTuntityo, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TyosuorituksenTuntityoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insert(TyosuorituksenTuntityo tyot) {
        Integer suoritusid = tyot.getSuoritusID();
        String tyontyyppi = tyot.getTyonTyyppi();
        Double tuntilkm = tyot.getTuntiLkm();
        Double aleprosentti = tyot.getAleProsentti();
        final String sql =  "INSERT INTO tyosuorituksenTuntityo " +
                            "VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{suoritusid, tyontyyppi, tuntilkm, aleprosentti});
    }

    @Override
    public List<TyosuorituksenTuntityo> selectAll() {
        final String sql =  "SELECT suoritusid, tyontyyppi, tuntilkm, aleprosentti " +
                            "FROM tyosuorituksenTuntityo";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            String tyontyyppi = rs.getString("tyontyyppi");
            Double tuntilkm = rs.getDouble("tuntilkm");
            Double aleprosentti = rs.getDouble("aleprosentti");
            return new TyosuorituksenTuntityo(suoritusid, tyontyyppi, tuntilkm, aleprosentti);
        });
    }

    @Override
    public Optional<TyosuorituksenTuntityo> selectById(Integer key) {
        final String sql =  "SELECT suoritusid, tyontyyppi, tuntilkm, aleprosentti " +
                            "FROM tyosuorituksenTuntityo " +
                            "WHERE suoritusid = ?";
        TyosuorituksenTuntityo tyot = jdbcTemplate.queryForObject(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            String tyontyyppi = rs.getString("tyontyyppi");
            Double tuntilkm = rs.getDouble("tuntilkm");
            Double aleprosentti = rs.getDouble("aleprosentti");
            return new TyosuorituksenTuntityo(suoritusid, tyontyyppi, tuntilkm, aleprosentti);
        });  
        return Optional.ofNullable(tyot);
    }

    @Override
    public int deleteById(Integer key) {
        final String sql =  "DELETE FROM tyosuorituksenTuntityo " +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.update(sql, new Object[]{key});
    }

    @Override
    public int updateById(Integer key, TyosuorituksenTuntityo tyot) {
        Integer suoritusid = tyot.getSuoritusID();
        String tyontyyppi = tyot.getTyonTyyppi();
        Double tuntilkm = tyot.getTuntiLkm();
        Double aleprosentti = tyot.getAleProsentti();
        final String sql =  "UPDATE tyosopimus " +
                            "SET suoritusid = ?, tyontyyppi = ?, tuntilkm = ?, aleprosentti = ?";
        return jdbcTemplate.update(sql, new Object[]{suoritusid, tyontyyppi, tuntilkm, aleprosentti, key});
    }

    public List<TyosuorituksenTuntityo> selectAllBySuoritusId(Integer key) {
        final String sql =  "SELECT suoritusid, tyontyyppi, tuntilkm, aleprosentti " +
                            "FROM tyosuorituksenTuntityo" +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.query(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            String tyontyyppi = rs.getString("tyontyyppi");
            Double tuntilkm = rs.getDouble("tuntilkm");
            Double aleprosentti = rs.getDouble("aleprosentti");
            return new TyosuorituksenTuntityo(suoritusid, tyontyyppi, tuntilkm, aleprosentti);
        });
    }

}
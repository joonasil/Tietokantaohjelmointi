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
        Integer suoritusID = tyot.getSuoritusID();
        String tyonTyyppi = tyot.getTyonTyyppi();
        Double tuntiLkm = tyot.getTuntiLkm();
        Double aleProsentti = tyot.getAleProsentti();
        final String sql = "INSERT INTO tyosuorituksenTuntityo VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{suoritusID, tyonTyyppi, tuntiLkm, aleProsentti});
    }

    @Override
    public List<TyosuorituksenTuntityo> selectAll() {
        final String sql = "SELECT suoritusID, tyonTyyppi, tuntiLkm, aleProsentti FROM tyosuorituksenTuntityo";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            String tyonTyyppi = rs.getString("tyonTyyppi");
            Double tuntiLkm = rs.getDouble("tuntiLkm");
            Double aleProsentti = rs.getDouble("aleProsentti");
            return new TyosuorituksenTuntityo(suoritusID, tyonTyyppi, tuntiLkm, aleProsentti);
        });
    }

    @Override
    public Optional<TyosuorituksenTuntityo> selectById(Integer key) {
        final String sql = "SELECT suoritusID, tyonTyyppi, tuntiLkm, aleProsentti FROM tyosuorituksenTuntityo WHERE suoritusID = ?";
        TyosuorituksenTuntityo tyot = jdbcTemplate.queryForObject(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            String tyonTyyppi = rs.getString("tyonTyyppi");
            Double tuntiLkm = rs.getDouble("tuntiLkm");
            Double aleProsentti = rs.getDouble("aleProsentti");
            return new TyosuorituksenTuntityo(suoritusID, tyonTyyppi, tuntiLkm, aleProsentti);
        });  
        return Optional.ofNullable(tyot);
    }

    @Override
    public int deleteById(Integer key) {
        final String sql = "DELETE FROM tyosuorituksenTuntityo WHERE suoritusID = ?";
        return jdbcTemplate.update(sql, new Object[]{key});
    }

    @Override
    public int updateById(Integer key, TyosuorituksenTuntityo tyot) {
        Integer suoritusID = tyot.getSuoritusID();
        String tyonTyyppi = tyot.getTyonTyyppi();
        Double tuntiLkm = tyot.getTuntiLkm();
        Double aleProsentti = tyot.getAleProsentti();
        final String sql = "UPDATE tyosopimus SET suoritusID = ?, tyonTyyppi = ?, tuntiLkm = ?, aleProsentti = ?";
        return jdbcTemplate.update(sql, new Object[]{suoritusID, tyonTyyppi, tuntiLkm, aleProsentti, key});
    }

}
package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tarvikeluettelo;

@Repository("luetteloPSQL")
public class TarvikeluetteloDao implements GenericDao<Tarvikeluettelo, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TarvikeluetteloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tarvikeluettelo luettelo) {
        Integer suoritusid = luettelo.getSuoritusID();
        Integer tarvikeid = luettelo.getTarvikeID();
        Integer lkm = luettelo.getLkm();
        Integer aleprosentti = luettelo.getAleProsentti();
        final String sql =  "INSERT INTO tarvikeluettelo " +
                            "VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{suoritusid, tarvikeid, lkm, aleprosentti});
    }

    @Override
    public List<Tarvikeluettelo> selectAll() {
        final String sql =  "SELECT suoritusid, tarvikeid, lkm, aleprosentti " +
                            "FROM tarvikeluettelo";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            Integer tarvikeid = rs.getInt("tarvikeid");
            Integer lkm = rs.getInt("lkm");
            Integer aleprosentti = rs.getInt("aleprosentti");
            return new Tarvikeluettelo(suoritusid, tarvikeid, lkm, aleprosentti);
        });
    }

    @Override
    public Optional<Tarvikeluettelo> selectById(Integer key) {
        final String sql =  "SELECT suoritusid, tarvikeid, lkm, aleprosentti " +
                            "FROM tarvikeluettelo" +
                            "WHERE suoritusid = ?";
        Tarvikeluettelo luettelo = jdbcTemplate.queryForObject(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            Integer tarvikeid = rs.getInt("tarvikeid");
            Integer lkm = rs.getInt("lkm");
            Integer aleprosentti = rs.getInt("aleprosentti");
            return new Tarvikeluettelo(suoritusid, tarvikeid, lkm, aleprosentti);
        });
        return Optional.ofNullable(luettelo);
    }

    @Override
    public int deleteById(Integer key) {
        final String sql =  "DELETE FROM tarvikeluettelo " +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.update(sql, new Object[]{key});
    }

    @Override
    public int updateById(Integer key, Tarvikeluettelo luettelo) {
        Integer suoritusid = luettelo.getSuoritusID();
        Integer tarvikeid = luettelo.getTarvikeID();
        Integer lkm = luettelo.getLkm();
        Integer aleprosentti = luettelo.getAleProsentti();
        final String sql =  "UPDATE tarvikeluettelo " +
                            "SET suoritusid = ?, tarvikeid = ?, lkm = ?, aleprosentti = ?" +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.update(sql, new Object[]{suoritusid, tarvikeid, lkm, aleprosentti, key});
    }

    public List<Tarvikeluettelo> selectAllBySuoritusId(Integer key) {
        final String sql =  "SELECT suoritusid, tarvikeid, lkm, aleprosentti " +
                            "FROM tarvikeluettelo " +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.query(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            Integer tarvikeid = rs.getInt("tarvikeid");
            Integer lkm = rs.getInt("lkm");
            Integer aleprosentti = rs.getInt("aleprosentti");
            return new Tarvikeluettelo(suoritusid, tarvikeid, lkm, aleprosentti);
        });
    }

    public List<Tarvikeluettelo> selectAllBySopimusIdGrouped(Integer key) {
        final String sql =  "SELECT tarvikeid, sum(lkm) as lkm, aleprosentti " +
                            "FROM ( " +
                                "SELECT * " +
                                "FROM tarvikeluettelo, tyosuoritus " +
                                "WHERE tarvikeluettelo.suoritusid = tyosuoritus.suoritusid " +
                                "AND tyosuoritus.sopimusid = ? " +
                                ") trvk " +
                            "GROUP BY tarvikeid, aleprosentti";
        return jdbcTemplate.query(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusid = null;
            Integer tarvikeid = rs.getInt("tarvikeid");
            Integer lkm = rs.getInt("lkm");
            Integer aleprosentti = rs.getInt("aleprosentti");
            return new Tarvikeluettelo(suoritusid, tarvikeid, lkm, aleprosentti);
        });
    }
}
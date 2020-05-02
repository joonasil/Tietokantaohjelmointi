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
        Integer suoritusID = luettelo.getSuoritusID();
        Integer tarvikeID = luettelo.getTarvikeID();
        Integer lkm = luettelo.getLkm();
        Integer aleProsentti = luettelo.getAleProsentti();
        final String sql =  "INSERT INTO tarvikeluettelo " +
                            "VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{suoritusID, tarvikeID, lkm, aleProsentti});
    }

    @Override
    public List<Tarvikeluettelo> selectAll() {
        final String sql =  "SELECT suoritusID, tarvikeID, lkm, aleProsentti " +
                            "FROM tarvikeluettelo";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            Integer tarvikeID = rs.getInt("tarvikeID");
            Integer lkm = rs.getInt("lkm");
            Integer aleProsentti = rs.getInt("aleProsentti");
            return new Tarvikeluettelo(suoritusID, tarvikeID, lkm, aleProsentti);
        });
    }

    @Override
    public Optional<Tarvikeluettelo> selectById(Integer key) {
        final String sql =  "SELECT suoritusID, tarvikeID, lkm, aleProsentti " +
                            "FROM tarvikeluettelo" +
                            "WHERE suoritusID = ?";
        Tarvikeluettelo luettelo = jdbcTemplate.queryForObject(sql, new Object[]{key}, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            Integer tarvikeID = rs.getInt("tarvikeID");
            Integer lkm = rs.getInt("lkm");
            Integer aleProsentti = rs.getInt("aleProsentti");
            return new Tarvikeluettelo(suoritusID, tarvikeID, lkm, aleProsentti);
        });
        return Optional.ofNullable(luettelo);
    }

    @Override
    public int deleteById(Integer key) {
        final String sql =  "DELETE FROM tarvikeluettelo " +
                            "WHERE suoritusID = ?";
        return jdbcTemplate.update(sql, new Object[]{key});
    }

    @Override
    public int updateById(Integer key, Tarvikeluettelo luettelo) {
        Integer suoritusID = luettelo.getSuoritusID();
        Integer tarvikeID = luettelo.getTarvikeID();
        Integer lkm = luettelo.getLkm();
        Integer aleProsentti = luettelo.getAleProsentti();
        final String sql =  "UPDATE tarvikeluettelo " +
                            "SET suoritusID = ?, tarvikeID = ?, lkm = ?, aleProsentti = ?" +
                            "WHERE suoritusID = ?";
        return jdbcTemplate.update(sql, new Object[]{suoritusID, tarvikeID, lkm, aleProsentti, key});
    }
}
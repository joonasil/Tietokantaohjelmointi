package group32.tikoht.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tarvike;

@Repository("tarvikePSQL")
public class TarvikeDao implements GenericDao<Tarvike, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TarvikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tarvike tarvike) {
        String nimi = tarvike.getNimi();
        Double sisaanostohinta = tarvike.getSisaanostohinta();
        Double myyntihinta = tarvike.getMyyntihinta();
        String yksikko = tarvike.getYksikko();
        Integer varastoLkm = tarvike.getVarastoLkm();
        Integer alv = tarvike.getAlv();
        final String sql = "INSERT INTO tarvike(nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv) VALUES(?, ?, ?, ?::yksikot, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv});
    }

    @Override
    public List<Tarvike> selectAll() {
        final String sql = "SELECT tarvikeID, nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv FROM tarvike";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer tarvikeID = rs.getInt("tarvikeID");
            String nimi = rs.getString("nimi");
            Double sisaanostohinta = rs.getDouble("sisaanostohinta");
            Double myyntihinta = rs.getDouble("myyntihinta");
            String yksikko = rs.getString("yksikko");
            Integer varastoLkm = rs.getInt("varastoLkm");
            Integer alv = rs.getInt("alv");
            return new Tarvike(tarvikeID, nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv );
        });
    }

    @Override
    public Optional<Tarvike> selectById(Integer key) {
        final String sql = "SELECT tarvikeID, nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv FROM tarvike WHERE tarvikeID = ?";
        Tarvike tarvike = jdbcTemplate.queryForObject(sql,new Object[]{key} ,(rs, i) -> {
            Integer tarvikeID = rs.getInt("tarvikeID");
            String nimi = rs.getString("nimi");
            Double sisaanostohinta = rs.getDouble("sisaanostohinta");
            Double myyntihinta = rs.getDouble("myyntihinta");
            String yksikko = rs.getString("yksikko");
            Integer varastoLkm = rs.getInt("varastoLkm");
            Integer alv = rs.getInt("alv");
            return new Tarvike(tarvikeID, nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv );
        });
        return Optional.ofNullable(tarvike);
    }

    @Override
    public int deleteById(Integer key) {
        final String sql = "DELETE FROM tarvike WHERE tarvikeID = ?";
        return jdbcTemplate.update(sql, new Object[]{key});
    }

    @Override
    public int updateById(Integer key, Tarvike tarvike) {
        String nimi = tarvike.getNimi();
        Double sisaanostohinta = tarvike.getSisaanostohinta();
        Double myyntihinta = tarvike.getMyyntihinta();
        String yksikko = tarvike.getYksikko();
        Integer varastoLkm = tarvike.getVarastoLkm();
        Integer alv = tarvike.getAlv();
        final String sql =  "UPDATE asiakas " + 
                            "SET nimi = ?, sisaanostohinta = ?, myyntihinta = ?, yksikko = ?::yksikot, varastoLkm = ?, alv = ? " +
                            "WHERE tarvikeID = ?";
        return jdbcTemplate.update(sql, new Object[]{nimi, sisaanostohinta, myyntihinta, yksikko, varastoLkm, alv, key});
    }

}
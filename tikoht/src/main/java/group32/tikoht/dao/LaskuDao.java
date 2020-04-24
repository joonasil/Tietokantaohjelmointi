package group32.tikoht.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Lasku;

@Repository("laskuPSQL")
public class LaskuDao implements GenericDao<Lasku, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LaskuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Lasku lasku) {
        Integer sopimusID = lasku.getSopimusID();
        LocalDate pvm = lasku.getPvm();
        LocalDate erapaiva = lasku.getErapaiva();
        LocalDate maksettuPvm = lasku.getMaksettuPvm();
        Integer edeltavaLasku = lasku.getEdeltavaLasku();
        Integer muistutusLkm = lasku.getMuistutusLkm();
        Double viivastyskulut = lasku.getViivastyskulut();
        final String sql = "INSERT INTO lasku(sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut)" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[]{sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut});
    }

    @Override
    public List<Lasku> selectAll() {
        final String sql =  "SELECT laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut " +
                            "FROM lasku";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer laskuID = rs.getInt("laskuID");
            Integer sopimusID = rs.getInt("sopimusID");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            LocalDate erapaiva = rs.getObject("erapaiva", LocalDate.class);
            LocalDate maksettuPvm = rs.getObject("maksettuPvm", LocalDate.class);
            Integer edeltavaLasku = rs.getInt("edeltavaLasku");
            Integer muistutusLkm = rs.getInt("muistutusLkm");
            Double viivastyskulut = rs.getDouble("viivastyskulut");
            return new Lasku(laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut);
        });
    }

    @Override
    public Optional<Lasku> selectById(Integer id) {
        final String sql =  "SELECT laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut " +
                            "FROM lasku " +
                            "WHERE laskuID = ?";
        Lasku lasku = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer laskuID = rs.getInt("laskuID");
            Integer sopimusID = rs.getInt("sopimusID");
            LocalDate pvm = rs.getObject("pvm", LocalDate.class);
            LocalDate erapaiva = rs.getObject("erapaiva", LocalDate.class);
            LocalDate maksettuPvm = rs.getObject("maksettuPvm", LocalDate.class);
            Integer edeltavaLasku = rs.getInt("edeltavaLasku");
            Integer muistutusLkm = rs.getInt("muistutusLkm");
            Double viivastyskulut = rs.getDouble("viivastyskulut");
            return new Lasku(laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut);
        });
        return Optional.ofNullable(lasku);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM lasku " +
                            "WHERE laskuID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Lasku lasku) {
        Integer sopimusID = lasku.getSopimusID();
        LocalDate pvm = lasku.getPvm();
        LocalDate erapaiva = lasku.getErapaiva();
        LocalDate maksettuPvm = lasku.getMaksettuPvm();
        Integer edeltavaLasku = lasku.getEdeltavaLasku();
        Integer muistutusLkm = lasku.getMuistutusLkm();
        Double viivastyskulut = lasku.getViivastyskulut();
        final String sql =  "UPDATE lasku " +
                            "SET sopimusID = ?, pvm = ?, erapaiva = ?, maksettuPvm = ?, edeltavaLasku = ?, muistutusLkm = ?, viivastyskulut = ? " +
                            "WHERE laskuID = ?";
        return jdbcTemplate.update(sql, new Object[]{sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut, id});
    }

}
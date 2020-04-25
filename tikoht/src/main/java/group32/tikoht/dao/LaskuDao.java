package group32.tikoht.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import group32.tikoht.model.Tyosopimus;
import group32.tikoht.service.TyosopimusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Lasku;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public List<Lasku> selectAllOverdue() {
        LocalDate localDate = java.time.LocalDate.now();
        final String sql =  "SELECT laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut " +
                "FROM lasku " +
                "WHERE erapaiva < ? AND maksettuPVM ISNULL";
        return jdbcTemplate.query(sql, new Object[]{localDate}, (rs, i) -> {
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

    public int generateOverdueInvoices() {
        List<Lasku> overdueInveoices = selectAllOverdue();
        List<Integer> unremaindedInvoices = new ArrayList<Integer>();

        for (Lasku formerInvoice : overdueInveoices) {
           unremaindedInvoices.add(formerInvoice.getLaskuID());
           if (unremaindedInvoices.contains(formerInvoice.getEdeltavaLasku())) {
               unremaindedInvoices.remove(formerInvoice.getEdeltavaLasku());
           }
        }
        System.out.println(unremaindedInvoices);

        LocalDate localDate = java.time.LocalDate.now();
        int inserted = 0;
        for (Lasku formerInvoice : overdueInveoices) {
            if (unremaindedInvoices.contains(formerInvoice.getLaskuID())) {

                Integer laskuID = null;
                Integer sopimusID = formerInvoice.getSopimusID();
                LocalDate pvm = localDate;
                LocalDate erapaiva = localDate.plusDays(30);
                LocalDate maksettuPvm = null;
                Integer edeltavaLasku = formerInvoice.getLaskuID();
                Integer muistutusLkm = formerInvoice.getMuistutusLkm() == null ? 1 : formerInvoice.getMuistutusLkm() + 1;
                Double viivastyskulut, viivastyskorko;
                if (muistutusLkm >= 2) {
                    Double summa = jdbcTemplate.query("SELECT (tyonHinta + tarvikkeidenHinta) AS summa FROM tyosopimus WHERE sopimusID = ?", new Object[]{formerInvoice.getSopimusID()}, (rs, i) -> {return rs.getDouble("summa");}).get(0);
                    System.out.println("edellinen: " + formerInvoice.getSopimusID() + " : summa " + summa);
                    viivastyskorko = (0.16 * (DAYS.between(localDate, formerInvoice.getErapaiva()) / 365) * summa);
                    viivastyskulut = formerInvoice.getViivastyskulut() + 5 + viivastyskorko;
                }
                else {
                    viivastyskulut = formerInvoice.getViivastyskulut() + 5;
                }

                Lasku reminder = new Lasku(laskuID, sopimusID, pvm, erapaiva, maksettuPvm, edeltavaLasku, muistutusLkm, viivastyskulut);
                if (insert(reminder) == 1) {
                    inserted++;
                }
            }
        }
        return inserted;
    }
}
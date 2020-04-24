package group32.tikoht.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import group32.tikoht.model.Tyosuoritus;

@Repository("tyosuoritusPSQL")
public class TyosuoritusDao implements GenericDao<Tyosuoritus, Integer> {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TyosuoritusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(Tyosuoritus tyosuoritus) {
        Integer sopimusID = tyosuoritus.getSopimusID();
        LocalDate suoritusPvm = tyosuoritus.getSuoritusPvm();
        final String sql =  "INSERT INTO tyosuoritus(sopimusID, suoritusPvm) " +
                            "VALUES(?, ?)";
        return jdbcTemplate.update(sql, new Object[]{sopimusID, suoritusPvm});
    }

    @Override
    public List<Tyosuoritus> selectAll() {
        final String sql =  "SELECT suoritusID, sopimusID, suoritusPvm " +
                            "FROM tyosuoritus";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            Integer sopimusID = rs.getInt("sopimusID");
            LocalDate suoritusPvm = rs.getObject("suoritusPvm", LocalDate.class);
            return new Tyosuoritus(suoritusID, sopimusID, suoritusPvm);
        });
    }

    @Override
    public Optional<Tyosuoritus> selectById(Integer id) {
        final String sql =  "SELECT suoritusID, sopimusID, suoritusPvm " +
                            "FROM tyosuoritus " +
                            "WHERE suoritusID = ?";
        Tyosuoritus tyosuoritus = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer suoritusID = rs.getInt("suoritusID");
            Integer sopimusID = rs.getInt("sopimusID");
            LocalDate suoritusPvm = rs.getObject("suoritusPvm", LocalDate.class);
            return new Tyosuoritus(suoritusID, sopimusID, suoritusPvm);
        });
        return Optional.ofNullable(tyosuoritus);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyosuoritus " +
                            "WHERE suoritusID = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyosuoritus tyosuoritus) {
        Integer sopimusID = tyosuoritus.getSopimusID();
        LocalDate suoritusPvm = tyosuoritus.getSuoritusPvm();
        final String sql =  "UPDATE tyosuoritus " +
                            "SET sopimusID = ?, suoritusPvm = ? " +
                            "WHERE suoritusID = ?";
        return jdbcTemplate.update(sql, new Object[]{sopimusID, suoritusPvm, id});
    }



}
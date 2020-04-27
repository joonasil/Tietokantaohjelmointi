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
        Integer sopimusid = tyosuoritus.getSopimusid();
        LocalDate suorituspvm = tyosuoritus.getSuorituspvm();
        final String sql =  "INSERT INTO tyosuoritus(sopimusid, suorituspvm) " +
                            "VALUES(?, ?)";
        return jdbcTemplate.update(sql, new Object[]{sopimusid, suorituspvm});
    }

    @Override
    public List<Tyosuoritus> selectAll() {
        final String sql =  "SELECT suoritusid, sopimusid, suorituspvm " +
                            "FROM tyosuoritus";
        return jdbcTemplate.query(sql, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            Integer sopimusid = rs.getInt("sopimusid");
            LocalDate suorituspvm = rs.getObject("suorituspvm", LocalDate.class);
            return new Tyosuoritus(suoritusid, sopimusid, suorituspvm);
        });
    }

    @Override
    public Optional<Tyosuoritus> selectById(Integer id) {
        final String sql =  "SELECT suoritusid, sopimusid, suorituspvm " +
                            "FROM tyosuoritus " +
                            "WHERE suoritusid = ?";
        Tyosuoritus tyosuoritus = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, i) -> {
            Integer suoritusid = rs.getInt("suoritusid");
            Integer sopimusid = rs.getInt("sopimusid");
            LocalDate suorituspvm = rs.getObject("suorituspvm", LocalDate.class);
            return new Tyosuoritus(suoritusid, sopimusid, suorituspvm);
        });
        return Optional.ofNullable(tyosuoritus);
    }

    @Override
    public int deleteById(Integer id) {
        final String sql =  "DELETE FROM tyosuoritus " +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int updateById(Integer id, Tyosuoritus tyosuoritus) {
        Integer sopimusid = tyosuoritus.getSopimusid();
        LocalDate suorituspvm = tyosuoritus.getSuorituspvm();
        final String sql =  "UPDATE tyosuoritus " +
                            "SET sopimusid = ?, suorituspvm = ? " +
                            "WHERE suoritusid = ?";
        return jdbcTemplate.update(sql, new Object[]{sopimusid, suorituspvm, id});
    }



}
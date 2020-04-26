package group32.tikoht.dao;

import group32.tikoht.model.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("tableMetadataPSQL")
public class TableMetadataDataAccessService implements TableMetadataDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableMetadataDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TableMetadata> selectMetadataByTableName(String tableName) {
        final String sql = "SELECT columns.table_name, columns.column_name, columns.data_type, columns.is_nullable " +
                            "FROM information_schema.columns " +
                            "WHERE table_schema = 'tikoht' " +
                            "AND columns.table_name = ? ";
        return jdbcTemplate.query(sql, new Object[]{tableName}, (rs, i) -> {
            String table_name = rs.getString("table_name");
            String column_name = rs.getString("column_name");
            String data_type = rs.getString("data_type");
            Boolean is_nullable = (rs.getString("is_nullable") == "YES");
            return new TableMetadata(table_name, column_name, data_type, is_nullable);
        });
    }
    
}

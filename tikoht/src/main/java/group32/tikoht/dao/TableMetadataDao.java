package group32.tikoht.dao;

import group32.tikoht.model.TableMetadata;

import java.util.List;
import java.util.Optional;

public interface TableMetadataDao {

    List<TableMetadata> selectMetadataByTableName(String tableName);
}

package group32.tikoht.dao;

import java.util.List;

import group32.tikoht.model.TableMetadata;

public interface TableMetadataDao {

    public List<TableMetadata> selectMetadataByTableName(String tableName);
    
}
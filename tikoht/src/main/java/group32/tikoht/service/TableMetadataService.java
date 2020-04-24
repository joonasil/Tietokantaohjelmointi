package group32.tikoht.service;

import java.util.List;

import group32.tikoht.dao.TableMetadataDao;
import group32.tikoht.model.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



@Service
public class TableMetadataService {

    private final TableMetadataDao tableMetadataDao;

    @Autowired
    public TableMetadataService (@Qualifier("tableMetadataPSQL") TableMetadataDao tableMetadataDao) {
        this.tableMetadataDao = tableMetadataDao;
    }

    public List<TableMetadata> getTableMetadataByTableName(String tableName) {
        return tableMetadataDao.selectMetadataByTableName(tableName);
    }

}
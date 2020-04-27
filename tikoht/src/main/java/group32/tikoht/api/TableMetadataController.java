package group32.tikoht.api;

import group32.tikoht.model.TableMetadata;
import group32.tikoht.service.TableMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("api/v1/metadata")
@RestController
public class TableMetadataController {

    private final TableMetadataService tableMetadataService;

    @Autowired
    public TableMetadataController(TableMetadataService tableMetadataService) {
        this.tableMetadataService = tableMetadataService;
    }

    @GetMapping(path = "{tableName}")
    public List<TableMetadata> getTableMetadataByTableName(@PathVariable("tableName") String tableName) {
        return tableMetadataService.getTableMetadataByTableName(tableName);
    }
}
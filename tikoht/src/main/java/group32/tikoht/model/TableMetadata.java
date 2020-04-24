package group32.tikoht.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TableMetadata {

    private final String table_name;
    private final String column_name;
    private final String data_type;
    private final boolean is_nullable;

    public TableMetadata(   @JsonProperty("table_name") String table_name, 
                            @JsonProperty("column_name") String column_name,
                            @JsonProperty("data_type") String data_type,
                            @JsonProperty("is_nullable") Boolean is_nullable) {
        this.table_name = table_name;
        this.column_name = column_name;
        this.data_type = data_type;
        this.is_nullable = is_nullable;
    }

    public boolean isIs_nullable() {
        return is_nullable;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public String getData_type() {
        return data_type;
    }
}
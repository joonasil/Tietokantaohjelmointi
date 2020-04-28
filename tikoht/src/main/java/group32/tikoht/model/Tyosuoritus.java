package group32.tikoht.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

public class Tyosuoritus {

    private final Integer suoritusid;
    private final Integer sopimusid;
    // @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate suorituspvm;

    public Tyosuoritus( @JsonProperty("suoritusid") Integer suoritusid,
                        @JsonProperty("sopimusid") Integer sopimusid,
                        @JsonProperty("suorituspvm") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate suorituspvm) {
        this.suoritusid = suoritusid;
        this.sopimusid = sopimusid;
        this.suorituspvm = suorituspvm;
    }
    public Integer getSuoritusid() {
        return suoritusid;
    }

    public Integer getSopimusid() {
        return sopimusid;
    }
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getSuorituspvm() {
        return suorituspvm;
    }
}
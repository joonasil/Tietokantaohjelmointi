package group32.tikoht.model;


import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class Tyosuoritus {

    private final Integer suoritusid;
    private final Integer sopimusid;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
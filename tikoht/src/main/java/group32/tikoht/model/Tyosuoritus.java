package group32.tikoht.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyosuoritus {

    private final Integer suoritusid;
    private final Integer sopimusid;
    @NotBlank
    private final LocalDate suorituspvm;

    public Tyosuoritus( @JsonProperty("suoritusid") Integer suoritusid,
                        @JsonProperty("sopimusid") Integer sopimusid,
                        @JsonProperty("suorituspvm") LocalDate suorituspvm) {
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

    public LocalDate getSuorituspvm() {
        return suorituspvm;
    }
}
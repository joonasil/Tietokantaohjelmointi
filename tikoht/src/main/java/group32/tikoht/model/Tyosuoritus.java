package group32.tikoht.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyosuoritus {

    private final Integer suoritusID;
    private final Integer sopimusID;
    @NotBlank
    private final LocalDate suoritusPvm;

    public Tyosuoritus( @JsonProperty("suoritusID") Integer suoritusID,
                        @JsonProperty("sopimusID") Integer sopimusID,
                        @JsonProperty("suoritusPvm") LocalDate suoritusPvm) {
        this.suoritusID = suoritusID;
        this.sopimusID = sopimusID;
        this.suoritusPvm = suoritusPvm;
    }

    public Integer getSuoritusID() {
        return suoritusID;
    }

    public Integer getSopimusID() {
        return sopimusID;
    }

    public LocalDate getSuoritusPvm() {
        return suoritusPvm;
    }
}
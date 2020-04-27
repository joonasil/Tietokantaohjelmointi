package group32.tikoht.model;

import 	java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lasku {
    private final Integer laskuid;
    private final Integer sopimusid;
    @NotBlank
    private final LocalDate pvm;
    @NotBlank
    private final LocalDate erapaiva;
    private final LocalDate maksettupvm;
    private final Integer edeltavalasku;
    private final Integer muistutuslkm;
    private final Double viivastyskulut;

    public Lasku(   @JsonProperty("laskuid") Integer laskuid,
                    @JsonProperty("sopimusid") Integer sopimusid,
                    @JsonProperty("pvm") LocalDate pvm,
                    @JsonProperty("erapaiva") LocalDate erapaiva, 
                    @JsonProperty("maksettupvm") LocalDate maksettupvm,
                    @JsonProperty("edeltavalasku") Integer edeltavalasku,
                    @JsonProperty("muistutuslkm") Integer muistutuslkm,
                    @JsonProperty("viivastyskulut") Double viivastyskulut) {
        this.laskuid = laskuid;
        this.sopimusid = sopimusid;
        this.pvm = pvm;
        this.erapaiva = erapaiva;
        this.maksettupvm = maksettupvm;
        this.edeltavalasku = edeltavalasku;
        this.muistutuslkm = muistutuslkm;
        this.viivastyskulut = viivastyskulut;
    }

    public Integer getLaskuID() {
        return laskuid;
    }

    public Integer getSopimusID() {
        return sopimusid;
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public LocalDate getErapaiva() {
        return erapaiva;
    }

    public LocalDate getMaksettuPvm() {
        return maksettupvm;
    }

    public Integer getEdeltavaLasku() {
        return edeltavalasku;
    }

    public Integer getMuistutusLkm() {
        return muistutuslkm;
    }

    public Double getViivastyskulut() {
        return viivastyskulut;
    }
}
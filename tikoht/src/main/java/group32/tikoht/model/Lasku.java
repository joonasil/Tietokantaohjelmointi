package group32.tikoht.model;

import 	java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lasku {
    private final Integer laskuID;
    private final Integer sopimusID;
    @NotBlank
    private final LocalDate pvm;
    @NotBlank
    private final LocalDate erapaiva;
    private final LocalDate maksettuPvm;
    private final Integer edeltavaLasku;
    private final Integer muistutusLkm;
    private final Double viivastyskulut;

    public Lasku(   @JsonProperty("laskuID") Integer laskuID,
                    @JsonProperty("sopimusID") Integer sopimusID,
                    @JsonProperty("pvm") LocalDate pvm,
                    @JsonProperty("erapaiva") LocalDate erapaiva, 
                    @JsonProperty("maksettuPvm") LocalDate maksettuPvm, 
                    @JsonProperty("edeltavaLasku") Integer edeltavaLasku,
                    @JsonProperty("muistutusLkm") Integer muistutusLkm, 
                    @JsonProperty("viivastyskulut") Double viivastyskulut) {
        this.laskuID = laskuID;
        this.sopimusID = sopimusID;
        this.pvm = pvm;
        this.erapaiva = erapaiva;
        this.maksettuPvm = maksettuPvm;
        this.edeltavaLasku = edeltavaLasku;
        this.muistutusLkm = muistutusLkm;
        this.viivastyskulut = viivastyskulut;
    }

    public Integer getLaskuID() {
        return laskuID;
    }

    public Integer getSopimusID() {
        return sopimusID;
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public LocalDate getErapaiva() {
        return erapaiva;
    }

    public LocalDate getMaksettuPvm() {
        return maksettuPvm;
    }

    public Integer getEdeltavaLasku() {
        return edeltavaLasku;
    }

    public Integer getMuistutusLkm() {
        return muistutusLkm;
    }

    public Double getViivastyskulut() {
        return viivastyskulut;
    }
}
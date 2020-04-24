package group32.tikoht.model;

import 	java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyosopimus {

    private final Integer sopimusID;
    private final Integer kohdeID;
    @NotBlank
    private final String tyyppi;
    private final Double tyonHinta;
    private final Double tarvikkeidenHinta;
    private final Integer osamaksu;
    private final LocalDate pvm;
    @NotBlank
    private final String sopimuksenTila;
    private final String selite;

    public Tyosopimus(  @JsonProperty("sopimusID") Integer sopimusID,
                        @JsonProperty("kohdeID") Integer kohdeID,
                        @JsonProperty("tyyppi") @NotBlank String tyyppi,
                        @JsonProperty("tyonHinta") Double tyonHinta,
                        @JsonProperty("tarvikkeidenHinta") Double tarvikkeidenHinta,
                        @JsonProperty("osamaksu") Integer osamaksu,
                        @JsonProperty("pvm") LocalDate pvm,
                        @JsonProperty("sopimuksenTila") @NotBlank String sopimuksenTila,
                        @JsonProperty("selite") String selite) {
        this.sopimusID = sopimusID;
        this.kohdeID = kohdeID;
        this.tyyppi = tyyppi;
        this.tyonHinta = tyonHinta;
        this.tarvikkeidenHinta = tarvikkeidenHinta;
        this.osamaksu = osamaksu;
        this.pvm = pvm;
        this.sopimuksenTila = sopimuksenTila;
        this.selite = selite;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public String getSelite() {
        return selite;
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public Integer getOsamaksu() {
        return osamaksu;
    }

    public Double getTarvikkeidenHinta() {
        return tarvikkeidenHinta;
    }

    public Double getTyonHinta() {
        return tyonHinta;
    }

    public Integer getSopimusID() {
        return sopimusID;
    }

    public Integer getKohdeID() {
        return kohdeID;
    }

    public String getSopimuksenTila() {
        return sopimuksenTila;
    }
    
}
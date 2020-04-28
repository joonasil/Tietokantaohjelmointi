package group32.tikoht.model;

import 	java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class Tyosopimus {

    private final Integer sopimusid;
    private final Integer kohdeid;
    @NotBlank
    private final String tyyppi;
    private final Double tyonhinta;
    private final Double tarvikkeidenhinta;
    private final Integer osamaksu;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate pvm;
    @NotBlank
    private final String sopimuksentila;
    private final String selite;

    public Tyosopimus(  @JsonProperty("sopimusid") Integer sopimusid,
                        @JsonProperty("kohdeid") Integer kohdeid,
                        @JsonProperty("tyyppi") @NotBlank String tyyppi,
                        @JsonProperty("tyonhinta") Double tyonhinta,
                        @JsonProperty("tarvikkeidenhinta") Double tarvikkeidenhinta,
                        @JsonProperty("osamaksu") Integer osamaksu,
                        @JsonProperty("pvm") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate pvm,
                        @JsonProperty("sopimuksentila") @NotBlank String sopimuksentila,
                        @JsonProperty("selite") String selite) {
        this.sopimusid = sopimusid;
        this.kohdeid = kohdeid;
        this.tyyppi = tyyppi;
        this.tyonhinta = tyonhinta;
        this.tarvikkeidenhinta = tarvikkeidenhinta;
        this.osamaksu = osamaksu;
        this.pvm = pvm;
        this.sopimuksentila = sopimuksentila;
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

    public Double getTarvikkeidenhinta() {
        return tarvikkeidenhinta;
    }

    public Double getTyonHinta() {
        return tyonhinta;
    }

    public Integer getSopimusid() { return sopimusid; }

    public Integer getKohdeid() {
        return kohdeid;
    }

    public String getSopimuksentila() {
        return sopimuksentila;
    }

    // Omat metodi
    public Double getSopimuksenSumma() {
        return tarvikkeidenhinta + tyonhinta;
    }
    
}
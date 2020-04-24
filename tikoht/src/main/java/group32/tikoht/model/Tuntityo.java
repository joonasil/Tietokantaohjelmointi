package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tuntityo {

    private final String tyonTyyppi;
    @NotBlank
    private final Integer hinta;
    @NotBlank
    private final Integer alv;

    public Tuntityo( @JsonProperty("tyonTyyppi") String tyonTyyppi,
                    @JsonProperty("hinta") Integer hinta,
                    @JsonProperty("alv") Integer alv) {
        this.tyonTyyppi = tyonTyyppi;
        this.hinta = hinta;
        this.alv = alv;
    }

    public String getTyonTyyppi() {
        return tyonTyyppi;
    }

    public Integer getHinta() {
        return hinta;
    }

    public Integer getAlv() {
        return alv;
    }
}
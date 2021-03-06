package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tuntityo {

    private final String tyontyyppi;
    @NotBlank
    private final Double hinta;
    @NotBlank
    private final Integer alv;

    public Tuntityo( @JsonProperty("tyontyyppi") String tyontyyppi,
                    @JsonProperty("hinta") Double hinta,
                    @JsonProperty("alv") Integer alv) {
        this.tyontyyppi = tyontyyppi;
        this.hinta = hinta;
        this.alv = alv;
    }

    public String getTyonTyyppi() {
        return tyontyyppi;
    }

    public Double getHinta() {
        return hinta;
    }

    public Integer getAlv() {
        return alv;
    }
}
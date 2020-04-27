package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TyosuorituksenTuntityo {

    private final Integer suoritusid;
    private final String tyontyyppi;
    @NotBlank
    private final Double tuntilkm;
    private final Double aleprosentti;

    public TyosuorituksenTuntityo(  @JsonProperty("suoritusid") Integer suoritusid,
                                    @JsonProperty("tyontyyppi") String tyontyyppi,
                                    @JsonProperty("tuntilkm") Double tuntilkm,
                                    @JsonProperty("aleprosentti") Double aleprosentti) {
        this.suoritusid = suoritusid;
        this.tyontyyppi = tyontyyppi;
        this.tuntilkm = tuntilkm;
        this.aleprosentti = aleprosentti;
    }

    public Integer getSuoritusID() {
        return suoritusid;
    }

    public String getTyonTyyppi() {
        return tyontyyppi;
    }

    public Double getTuntiLkm() {
        return tuntilkm;
    }

    public Double getAleProsentti() {
        return aleprosentti;
    }
}
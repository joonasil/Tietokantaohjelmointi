package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TyosuorituksenTuntityo {

    private final Integer suoritusID;
    private final String tyonTyyppi;
    @NotBlank
    private final Double tuntiLkm;
    private final Double aleProsentti;

    public TyosuorituksenTuntityo(  @JsonProperty("suoritusID") Integer suoritusID,
                                    @JsonProperty("tyonTyyppi") String tyonTyyppi, 
                                    @JsonProperty("tuntiLkm") Double tuntiLkm,
                                    @JsonProperty("aleProsentti") Double aleProsentti) {
        this.suoritusID = suoritusID;
        this.tyonTyyppi = tyonTyyppi;
        this.tuntiLkm = tuntiLkm;
        this.aleProsentti = aleProsentti;
    }

    public Integer getSuoritusID() {
        return suoritusID;
    }

    public String getTyonTyyppi() {
        return tyonTyyppi;
    }

    public Double getTuntiLkm() {
        return tuntiLkm;
    }

    public Double getAleProsentti() {
        return aleProsentti;
    }
}
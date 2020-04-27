package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Asiakas {

    private final Integer asiakasid;
    @NotBlank
    private final String nimi;
    @NotBlank
    private final String osoite;

    public Asiakas( @JsonProperty("asikasID") Integer asiakasid,
                    @JsonProperty("nimi") String nimi,
                    @JsonProperty("osoite") String osoite) {
        this.asiakasid = asiakasid;
        this.nimi = nimi;
        this.osoite = osoite;
    }

    public Integer getAsiakasID() {
        return asiakasid;
    }

    public String getNimi() {
        return nimi;
    }

    public String getOsoite() {
        return osoite;
    }
}
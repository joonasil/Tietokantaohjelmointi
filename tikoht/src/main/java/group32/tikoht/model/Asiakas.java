package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Asiakas {

    private final Integer asiakasID;
    @NotBlank
    private final String nimi;
    @NotBlank
    private final String osoite;

    public Asiakas( @JsonProperty("asikasID") Integer asiakasID,
                    @JsonProperty("nimi") String nimi,
                    @JsonProperty("osoite") String osoite) {
        this.asiakasID = asiakasID;
        this.nimi = nimi;
        this.osoite = osoite;
    }

    public Integer getAsiakasID() {
        return asiakasID;
    }

    public String getNimi() {
        return nimi;
    }

    public String getOsoite() {
        return osoite;
    }
}
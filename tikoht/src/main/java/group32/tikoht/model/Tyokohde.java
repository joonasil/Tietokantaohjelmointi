package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyokohde {

    private final Integer kohdeID;
    private final Integer omistajaID;
    @NotBlank
    private final String kohdetyyppi;
    @NotBlank
    private final String osoite;

    public Tyokohde(@JsonProperty("kohdeID") Integer kohdeID,
                    @JsonProperty("omistajaID") Integer omistajaID,
                    @JsonProperty("kohdetyyppi") String kohdetyyppi,
                    @JsonProperty("osoite") String osoite) {
        this.kohdeID = kohdeID;
        this.omistajaID = omistajaID;
        this.kohdetyyppi = kohdetyyppi;
        this.osoite = osoite;
    }

    public Integer getKohdeID() {
        return kohdeID;
    }

    public Integer getOmistajaID() {
        return omistajaID;
    }

    public String getKohdetyyppi() {
        return kohdetyyppi;
    }

    public String getOsoite() {
        return osoite;
    }
}
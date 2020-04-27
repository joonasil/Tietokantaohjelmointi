package group32.tikoht.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tyokohde {

    private final Integer kohdeid;
    private final Integer omistajaid;
    @NotBlank
    private final String kohdetyyppi;

    @NotBlank
    private final String osoite;

    public Tyokohde(@JsonProperty("kohdeid") Integer kohdeid,
                    @JsonProperty("omistajaid") Integer omistajaid,
                    @JsonProperty("kohdetyyppi") String kohdetyyppi,
                    @JsonProperty("osoite") String osoite) {
        this.kohdeid = kohdeid;
        this.omistajaid = omistajaid;
        this.kohdetyyppi = kohdetyyppi;
        this.osoite = osoite;
    }

    public Integer getKohdeid() {
        return kohdeid;
    }

    public Integer getOmistajaid() {
        return omistajaid;
    }

    public String getKohdetyyppi() {
        return kohdetyyppi;
    }

    public String getOsoite() {
        return osoite;
    }
}
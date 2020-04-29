package group32.tikoht.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tuntityolasku {

    private final String asiakas;
    private final String asiakasosoite;
    private final String kohdeosoite;
    private final List<Tyosuoritus> tyosuoritukset;
    private final List<TyosuorituksenTuntityo> tyontiedot;
    private final List<Tarvikeluettelo> tarvikkeet;
    private final List<Tarvike> tarviketiedot;
    private final Double summa;

    public Tuntityolasku(   @JsonProperty("asiakas") String asiakas, 
                            @JsonProperty("asiakasosoite") String asiakasosoite, 
                            @JsonProperty("kohdeosoite") String kohdeosoite,
                            @JsonProperty("tyosuoritukset") List<Tyosuoritus> tyosuoritukset,
                            @JsonProperty("tyontiedot") List<TyosuorituksenTuntityo> tyontiedot, 
                            @JsonProperty("tarvikkeet") List<Tarvikeluettelo> tarvikkeet,
                            @JsonProperty("tarviketiedot") List<Tarvike> tarviketiedot,
                            @JsonProperty("summa") Double summa) {
        this.asiakas = asiakas;
        this.asiakasosoite = asiakasosoite;
        this.kohdeosoite = kohdeosoite;
        this.tyosuoritukset = tyosuoritukset;
        this.tyontiedot = tyontiedot;
        this.tarvikkeet = tarvikkeet;
        this.tarviketiedot = tarviketiedot;
        this.summa = summa;
    }

    public String getAsiakas() {
        return asiakas;
    }

    public String getAsiakasosoite() {
        return asiakasosoite;
    }

    public String getKohdeosoite() {
        return kohdeosoite;
    }

    public List<Tyosuoritus> getTyosuoritukset() {
        return tyosuoritukset;
    }

    public List<TyosuorituksenTuntityo> getTyontiedot() {
        return tyontiedot;
    }

    public List<Tarvikeluettelo> getTarvikkeet() {
        return tarvikkeet;
    }

    public Double getSumma () {
        return summa;
    }

}
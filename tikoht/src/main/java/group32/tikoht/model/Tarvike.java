package group32.tikoht.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarvike {

    private final Integer tarvikeid;
    private final String nimi;
    private final Double sisaanostohinta;
    private final Double myyntihinta;
    private final String yksikko;
    private final Integer varastolkm;
    private final Integer alv;

    public Tarvike( @JsonProperty("tarvikeid") Integer tarvikeid,
                    @JsonProperty("nimi") String nimi,
                    @JsonProperty("sisaanostohinta") Double sisaanostohinta, 
                    @JsonProperty("myyntihinta") Double myyntihinta, 
                    @JsonProperty("yksikko") String yksikko,
                    @JsonProperty("varastolkm") Integer varastolkm,
                    @JsonProperty("alv") Integer alv) {
        this.tarvikeid = tarvikeid;
        this.nimi = nimi;
        this.sisaanostohinta = sisaanostohinta;
        this.myyntihinta = myyntihinta;
        this.yksikko = yksikko;
        this.varastolkm = varastolkm;
        this.alv = alv;
    }

    public Integer getTarvikeID() {
        return tarvikeid;
    }

    public String getNimi() {
        return nimi;
    }

    public Double getSisaanostohinta() {
        return sisaanostohinta;
    }

    public Double getMyyntihinta() {
        return myyntihinta;
    }

    public String getYksikko() {
        return yksikko;
    }

    public Integer getVarastoLkm() {
        return varastolkm;
    }

    public Integer getAlv() {
        return alv;
    }

}
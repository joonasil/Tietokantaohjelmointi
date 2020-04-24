package group32.tikoht.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarvike {

    private final Integer tarvikeID;
    private final String nimi;
    private final Double sisaanostohinta;
    private final Double myyntihinta;
    private final String yksikko;
    private final Integer varastoLkm;
    private final Integer alv;

    public Tarvike( @JsonProperty("tarvikeID") Integer tarvikeID,
                    @JsonProperty("nimi") String nimi,
                    @JsonProperty("sisaanostohinta") Double sisaanostohinta, 
                    @JsonProperty("myyntihinta") Double myyntihinta, 
                    @JsonProperty("yksikko") String yksikko,
                    @JsonProperty("varastoLkm") Integer varastoLkm, 
                    @JsonProperty("alv") Integer alv) {
        this.tarvikeID = tarvikeID;
        this.nimi = nimi;
        this.sisaanostohinta = sisaanostohinta;
        this.myyntihinta = myyntihinta;
        this.yksikko = yksikko;
        this.varastoLkm = varastoLkm;
        this.alv = alv;
    }

    public Integer getTarvikeID() {
        return tarvikeID;
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
        return varastoLkm;
    }

    public Integer getAlv() {
        return alv;
    }

}
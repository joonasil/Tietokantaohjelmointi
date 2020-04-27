package group32.tikoht.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarvikeluettelo {

    private final Integer suoritusid;
    private final Integer tarvikeid;
    private final Integer lkm;
    private final Integer aleprosentti;

    public Tarvikeluettelo( @JsonProperty("suoritusid") Integer suoritusid,
                            @JsonProperty("tarvikeid") Integer tarvikeid,
                            @JsonProperty("lkm") Integer lkm,
                            @JsonProperty("aleprosentti") Integer aleprosentti) {
        this.suoritusid = suoritusid;
        this.tarvikeid = tarvikeid;
        this.lkm = lkm;
        this.aleprosentti = aleprosentti;
    }

    public Integer getSuoritusID() {
        return suoritusid;
    }

    public Integer getTarvikeID() {
        return tarvikeid;
    }

    public Integer getLkm() {
        return lkm;
    }

    public Integer getAleProsentti() {
        return aleprosentti;
    }


}
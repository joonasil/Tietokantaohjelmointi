package group32.tikoht.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tarvikeluettelo {

    private final Integer suoritusID;
    private final Integer tarvikeID;
    private final Integer lkm;
    private final Integer aleProsentti;

    public Tarvikeluettelo( @JsonProperty("suoritusID") Integer suoritusID,
                            @JsonProperty("tarvikeID") Integer tarvikeID,
                            @JsonProperty("lkm") Integer lkm,
                            @JsonProperty("aleProsentti") Integer aleProsentti) {
        this.suoritusID = suoritusID;
        this.tarvikeID = tarvikeID;
        this.lkm = lkm;
        this.aleProsentti = aleProsentti;
    }

    public Integer getSuoritusID() {
        return suoritusID;
    }

    public Integer getTarvikeID() {
        return tarvikeID;
    }

    public Integer getLkm() {
        return lkm;
    }

    public Integer getAleProsentti() {
        return aleProsentti;
    }


}
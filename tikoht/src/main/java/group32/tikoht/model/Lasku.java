package group32.tikoht.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class Lasku {
    private final Integer laskuid;
    private final Integer sopimusid;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate pvm;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate erapaiva;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate maksettupvm;
    private final Integer edeltavalasku;
    private final Integer muistutuslkm;
    private final Double viivastyskulut;

    public Lasku(   @JsonProperty("laskuid") Integer laskuid,
                    @JsonProperty("sopimusid") Integer sopimusid,
                    @JsonProperty("pvm") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate pvm,
                    @JsonProperty("erapaiva") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate erapaiva,
                    @JsonProperty("maksettupvm") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate maksettupvm,
                    @JsonProperty("edeltavalasku") Integer edeltavalasku,
                    @JsonProperty("muistutuslkm") Integer muistutuslkm,
                    @JsonProperty("viivastyskulut") Double viivastyskulut) {
        this.laskuid = laskuid;
        this.sopimusid = sopimusid;
        this.pvm = pvm;
        this.erapaiva = erapaiva;
        this.maksettupvm = maksettupvm;
        this.edeltavalasku = edeltavalasku;
        this.muistutuslkm = muistutuslkm;
        this.viivastyskulut = viivastyskulut;
    }

    public Integer getLaskuID() {
        return laskuid;
    }

    public Integer getSopimusID() {
        return sopimusid;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getPvm() {
        return pvm;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getErapaiva() {
        return erapaiva;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate getMaksettuPvm() {
        return maksettupvm;
    }

    public Integer getEdeltavaLasku() {
        return edeltavalasku;
    }

    public Integer getMuistutusLkm() {
        return muistutuslkm;
    }

    public Double getViivastyskulut() {
        return viivastyskulut;
    }
}
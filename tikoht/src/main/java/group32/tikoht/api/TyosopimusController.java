package group32.tikoht.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import group32.tikoht.service.AsiakasService;
import group32.tikoht.service.LaskuService;
import group32.tikoht.service.TyokohdeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Asiakas;
import group32.tikoht.model.Tuntityolasku;
import group32.tikoht.model.Tyokohde;
import group32.tikoht.model.Tyosopimus;
import group32.tikoht.model.TyosuorituksenTuntityo;
import group32.tikoht.model.Tyosuoritus;
import group32.tikoht.service.TyosopimusService;
import group32.tikoht.service.TyosuorituksenTuntityoService;
import group32.tikoht.service.TyosuoritusService;

@RequestMapping("api/v1/tyosopimus")
@RestController
public class TyosopimusController {

    private final TyosopimusService tyosopimusService;
    private final LaskuService laskuService;
    private final AsiakasService asiakasService;
    private final TyokohdeService kohdeService;
    private final TyosuoritusService tyosuoritusService;
    private final TyosuorituksenTuntityoService tyosuorService;

    @Autowired
    public TyosopimusController(TyosopimusService tyosopimusService, 
                                LaskuService laskuService,
                                AsiakasService asiakasService,
                                TyokohdeService kohdeService,
                                TyosuoritusService tyosuoritusService,
                                TyosuorituksenTuntityoService tyosuorService) {
        this.tyosopimusService = tyosopimusService;
        this.laskuService = laskuService;
        this.asiakasService = asiakasService;
        this.kohdeService = kohdeService;
        this.tyosuoritusService = tyosuoritusService;
        this.tyosuorService = tyosuorService;
    }

    @CrossOrigin
    @PostMapping
    public void addTyosopimus(@Valid @NotNull @RequestBody Tyosopimus tyosopimus) {
        tyosopimusService.addTyosopimus(tyosopimus);
    }
    @CrossOrigin
    @GetMapping
    public List<Tyosopimus> getAllTyosopimus() {
        return tyosopimusService.getAllTyosopimus();
    }

    @CrossOrigin
    @GetMapping(path = "{sopimusID}")
    public Tyosopimus getTyosopimusById(@PathVariable("sopimusID") Integer sopimusID) {
        return tyosopimusService.getTyosopimusById(sopimusID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{sopimusID}")
    public void deleteTyosopimusById(@PathVariable("sopimusID") Integer sopimusID) {
        tyosopimusService.deleteTyosopimus(sopimusID);
    }

    @CrossOrigin
    @PutMapping(path = "{sopimusID}")
    public void updateTyosopimus(@PathVariable("sopimusID") Integer sopimusID,@Valid @NotNull @RequestBody Tyosopimus tyosopimusToUpdate) {
        tyosopimusService.updateTyosopimus(sopimusID, tyosopimusToUpdate);
    }

    // TYOSOPIMUS OMAT
    @CrossOrigin
    @PostMapping(path = "{sopimusID}/lasku")
    public void makeInvoice(@PathVariable("SopimusID") Integer sopimusID, @NotNull List<Date> duedates) {
        Tyosopimus tyosopimus = getTyosopimusById((sopimusID));
        if (tyosopimus != null && duedates.size() == tyosopimus.getOsamaksu()) {
            // JÄI KESKEN - TÄSSÄ LUODAAN UUDET LASKUT JOISSA SUMMA JAETTU OSAMAKSUJEN MÄÄRÄLLÄ JA LASKUIHIN LAITETTU ANNEUTT PÄIVÄMÄÄRÄT ERÄPÄIVIKSI
        }
    }

    //Palauta JSON laskun erittelyä varten. Kesken
    @CrossOrigin
    @GetMapping(path = "{sopimusID}/lasku")
    public Tuntityolasku getTuntityolasku(@PathVariable("sopimusID") Integer sopimusID) {
        Tyosopimus tyosopimus = getTyosopimusById((sopimusID));
        Tyokohde kohde = kohdeService.getTyokohdeById(tyosopimus.getKohdeid()).get();
        Asiakas asiakastiedot = asiakasService.getAsiakasById(kohde.getOmistajaid()).get();
        final String asiakas = asiakastiedot.getNimi();
        final String asiakasosoite = asiakastiedot.getOsoite();
        final String kohdeosoite = kohde.getOsoite();
        final List<Tyosuoritus> tyosuoritukset = tyosuoritusService.getAllBySopimus(sopimusID);
        // final List<TyosuorituksenTuntityo> tyontiedot = tyosuorService.getAllBySuoritusId(suoritusId);
        // return new Tuntityolasku(null, null, null, null, null, null);
        return null;
    }

}
package group32.tikoht.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import group32.tikoht.service.LaskuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Tyosopimus;
import group32.tikoht.service.TyosopimusService;

@RequestMapping("api/v1/tyosopimus")
@RestController
public class TyosopimusController {

    private final TyosopimusService tyosopimusService;
    private final LaskuService laskuService;

    @Autowired
    public TyosopimusController(TyosopimusService tyosopimusService, LaskuService laskuService) {
        this.tyosopimusService = tyosopimusService;
        this.laskuService = laskuService;
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
    @PostMapping(path = "{sopimusID}")
    public void makeInvoice(@PathVariable("SopimusID") Integer sopimusID, @NotNull List<Date> duedates) {
        Tyosopimus tyosopimus = getTyosopimusById((sopimusID));
        if (tyosopimus != null && duedates.size() == tyosopimus.getOsamaksu()) {
            // JÄI KESKEN - TÄSSÄ LUODAAN UUDET LASKUT JOISSA SUMMA JAETTU OSAMAKSUJEN MÄÄRÄLLÄ JA LASKUIHIN LAITETTU ANNEUTT PÄIVÄMÄÄRÄT ERÄPÄIVIKSI
        }
    }

}
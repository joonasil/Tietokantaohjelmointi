package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Lasku;
import group32.tikoht.service.LaskuService;

@RequestMapping("api/v1/lasku")
@RestController
public class LaskuController {

    private final LaskuService laskuService;

    @Autowired
    public LaskuController(LaskuService laskuService) {
        this.laskuService = laskuService;
    }

    @CrossOrigin
    @PostMapping
    public void addLasku(@Valid @NotNull @RequestBody Lasku lasku) {
        laskuService.addLasku(lasku);
    }

    @CrossOrigin
    @GetMapping
    public List<Lasku> getAllLasku() {
        return laskuService.getAllLasku();
    }

    @CrossOrigin
    @GetMapping(path = "{laskuID}")
    public Lasku getLaskuById(@PathVariable("laskuID") Integer laskuID) {
        return laskuService.getLaskuById(laskuID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{laskuID}")
    public void deleteLaskuById(@PathVariable("laskuID") Integer laskuID) {
        laskuService.deleteLasku(laskuID);
    }

    @CrossOrigin
    @PutMapping(path = "{laskuID}")
    public void updateLasku(@PathVariable("laskuID") Integer laskuID,@Valid @NotNull @RequestBody Lasku laskuToUpdate) {
        laskuService.updateLasku(laskuID, laskuToUpdate);
    }

    // LASKUN OMAT
    @CrossOrigin
    @GetMapping(path = "eraantyneet")
    public List<Lasku> getAllOverdue() { return laskuService.getAllOverdue();}

    @PostMapping(path = "eraantyneet/muistuta")
    public int generateOverdueInvoices() { return laskuService.generateOverdueInvoices(); }
}
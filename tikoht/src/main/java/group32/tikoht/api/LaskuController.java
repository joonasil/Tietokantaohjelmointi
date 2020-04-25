package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public void addLasku(@Valid @NotNull @RequestBody Lasku lasku) {
        laskuService.addLasku(lasku);
    }

    @GetMapping
    public List<Lasku> getAllLasku() {
        return laskuService.getAllLasku();
    }

    @GetMapping(path = "{laskuID}")
    public Lasku getLaskuById(@PathVariable("laskuID") Integer laskuID) {
        return laskuService.getLaskuById(laskuID)
                .orElse(null);
    }

    @DeleteMapping(path = "{laskuID}")
    public void deleteLaskuById(@PathVariable("laskuID") Integer laskuID) {
        laskuService.deleteLasku(laskuID);
    }

    @PutMapping(path = "{laskuID}")
    public void updateLasku(@PathVariable("laskuID") Integer laskuID,@Valid @NotNull @RequestBody Lasku laskuToUpdate) {
        laskuService.updateLasku(laskuID, laskuToUpdate);
    }

    // LASKUN OMAT
    @GetMapping(path = "eraantyneet")
    public List<Lasku> getAllOverdue() { return laskuService.getAllOverdue();}

    @PostMapping(path = "eraantyneet/muistuta")
    public int generateOverdueInvoices() { return laskuService.generateOverdueInvoices(); }
}
package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Tarvikeluettelo;
import group32.tikoht.service.TarvikeluetteloService;

@RequestMapping("api/v1/tarvikeluettelo")
@RestController
public class TarvikeluetteloController {

    private final TarvikeluetteloService luetteloService;

    @Autowired
    public TarvikeluetteloController(TarvikeluetteloService luetteloService) {
        this.luetteloService = luetteloService;
    }

    @CrossOrigin
    @PostMapping
    public void addTarvikeluettelo(@Valid @NotNull @RequestBody Tarvikeluettelo luettelo) {
        luetteloService.addTarvikeluettelo(luettelo);
    }

    @CrossOrigin
    @GetMapping
    public List<Tarvikeluettelo> getAllTarvikeluettelo() {
        return luetteloService.getAllTarvikeluettelo();
    }

    @CrossOrigin
    @GetMapping(path = "{suoritusID}")
    public Tarvikeluettelo getTarvikeluetteloById(@PathVariable("suoritusID") Integer suoritusID) {
        return luetteloService.getTarvikeluetteloById(suoritusID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{suoritusID}")
    public void deleteTarvikeluetteloById(@PathVariable("suoritusID") Integer suoritusID) {
        luetteloService.deleteTarvikeluettelo(suoritusID);
    }

    @CrossOrigin
    @PutMapping(path = "{suoritusID}")
    public void updateTarvikeluettelo(@PathVariable("suoritusID") Integer suoritusID,@Valid @NotNull @RequestBody Tarvikeluettelo luetteloToUpdate) {
        luetteloService.updateTarvikeluettelo(suoritusID, luetteloToUpdate);
    }
}
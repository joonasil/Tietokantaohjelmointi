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

    @PostMapping
    public void addTarvikeluettelo(@Valid @NotNull @RequestBody Tarvikeluettelo luettelo) {
        luetteloService.addTarvikeluettelo(luettelo);
    }

    @GetMapping
    public List<Tarvikeluettelo> getAllTarvikeluettelo() {
        return luetteloService.getAllTarvikeluettelo();
    }

    @GetMapping(path = "{suoritusID}")
    public Tarvikeluettelo getTarvikeluetteloById(@PathVariable("suoritusID") Integer suoritusID) {
        return luetteloService.getTarvikeluetteloById(suoritusID)
                .orElse(null);
    }

    @DeleteMapping(path = "{suoritusID}")
    public void deleteTarvikeluetteloById(@PathVariable("suoritusID") Integer suoritusID) {
        luetteloService.deleteTarvikeluettelo(suoritusID);
    }

    @PutMapping(path = "{suoritusID}")
    public void updateTarvikeluettelo(@PathVariable("suoritusID") Integer suoritusID,@Valid @NotNull @RequestBody Tarvikeluettelo luetteloToUpdate) {
        luetteloService.updateTarvikeluettelo(suoritusID, luetteloToUpdate);
    }
}
package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Tyokohde;
import group32.tikoht.service.TyokohdeService;

@RequestMapping("api/v1/tyokohde")
@RestController
public class TyokohdeController {

    private final TyokohdeService tyokohdeService;

    @Autowired
    public TyokohdeController(TyokohdeService tyokohdeService) {
        this.tyokohdeService = tyokohdeService;
    }

    @CrossOrigin
    @PostMapping
    public void addTyokohde(@Valid @NotNull @RequestBody Tyokohde tyokohde) {
        tyokohdeService.addTyokohde(tyokohde);
    }

    @CrossOrigin
    @GetMapping
    public List<Tyokohde> getAllTyokohde() {
        return tyokohdeService.getAllTyokohde();
    }

    @CrossOrigin
    @GetMapping(path = "{kohdeID}")
    public Tyokohde getTyokohdeById(@PathVariable("kohdeID") Integer kohdeID) {
        return tyokohdeService.getTyokohdeById(kohdeID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{kohdeID}")
    public void deleteTyokohdeById(@PathVariable("kohdeID") Integer kohdeID) {
        tyokohdeService.deleteTyokohde(kohdeID);
    }

    @CrossOrigin
    @PutMapping(path = "{kohdeID}")
    public void updateTyokohde(@PathVariable("kohdeID") Integer kohdeID,@Valid @NotNull @RequestBody Tyokohde tyokohdeToUpdate) {
        tyokohdeService.updateTyokohde(kohdeID, tyokohdeToUpdate);
    }
}
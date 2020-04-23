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

    @PostMapping
    public void addTyokohde(@Valid @NotNull @RequestBody Tyokohde tyokohde) {
        tyokohdeService.addTyokohde(tyokohde);
    }

    @GetMapping
    public List<Tyokohde> getAllTyokohde() {
        return tyokohdeService.getAllTyokohde();
    }

    @GetMapping(path = "{kohdeID}")
    public Tyokohde getTyokohdeById(@PathVariable("kohdeID") Integer kohdeID) {
        return tyokohdeService.getTyokohdeById(kohdeID)
                .orElse(null);
    }

    @DeleteMapping(path = "{kohdeID}")
    public void deleteTyokohdeById(@PathVariable("kohdeID") Integer kohdeID) {
        tyokohdeService.deleteTyokohde(kohdeID);
    }

    @PutMapping(path = "{kohdeID}")
    public void updateTyokohde(@PathVariable("kohdeID") Integer kohdeID,@Valid @NotNull @RequestBody Tyokohde tyokohdeToUpdate) {
        tyokohdeService.updateTyokohde(kohdeID, tyokohdeToUpdate);
    }
}
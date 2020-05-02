package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Tyosuoritus;
import group32.tikoht.service.TyosuoritusService;

@RequestMapping("api/v1/tyosuoritus")
@RestController
public class TyosuoritusController {

    private final TyosuoritusService tyosuoritusService;

    @Autowired
    public TyosuoritusController(TyosuoritusService tyosuoritusService) {
        this.tyosuoritusService = tyosuoritusService;
    }

    @CrossOrigin
    @PostMapping
    public void addTyosuoritus(/*@Valid @NotNull*/ @RequestBody Tyosuoritus tyosuoritus) {
        tyosuoritusService.addTyosuoritus(tyosuoritus);
    }

    @CrossOrigin
    @GetMapping
    public List<Tyosuoritus> getAllTyosuoritus() {
        return tyosuoritusService.getAllTyosuoritus();
    }

    @CrossOrigin
    @GetMapping(path = "{suoritusID}")
    public Tyosuoritus getTyosuoritusById(@PathVariable("suoritusID") Integer suoritusID) {
        return tyosuoritusService.getTyosuoritusById(suoritusID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{suoritusID}")
    public void deleteTyosuoritusById(@PathVariable("suoritusID") Integer suoritusID) {
        tyosuoritusService.deleteTyosuoritus(suoritusID);
    }

    @CrossOrigin
    @PutMapping(path = "{suoritusID}")
    public void updateTyosuoritus(@PathVariable("suoritusID") Integer suoritusID,/*@Valid @NotNull*/ @RequestBody Tyosuoritus tyosuoritusToUpdate) {
        tyosuoritusService.updateTyosuoritus(suoritusID, tyosuoritusToUpdate);
    }
}
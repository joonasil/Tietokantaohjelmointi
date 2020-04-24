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

    @PostMapping
    public void addTyosuoritus(@Valid @NotNull @RequestBody Tyosuoritus tyosuoritus) {
        tyosuoritusService.addTyosuoritus(tyosuoritus);
    }

    @GetMapping
    public List<Tyosuoritus> getAllTyosuoritus() {
        return tyosuoritusService.getAllTyosuoritus();
    }

    @GetMapping(path = "{suoritusID}")
    public Tyosuoritus getTyosuoritusById(@PathVariable("suoritusID") Integer suoritusID) {
        return tyosuoritusService.getTyosuoritusById(suoritusID)
                .orElse(null);
    }

    @DeleteMapping(path = "{suoritusID}")
    public void deleteTyosuoritusById(@PathVariable("suoritusID") Integer suoritusID) {
        tyosuoritusService.deleteTyosuoritus(suoritusID);
    }

    @PutMapping(path = "{suoritusID}")
    public void updateTyosuoritus(@PathVariable("suoritusID") Integer suoritusID,@Valid @NotNull @RequestBody Tyosuoritus tyosuoritusToUpdate) {
        tyosuoritusService.updateTyosuoritus(suoritusID, tyosuoritusToUpdate);
    }
}
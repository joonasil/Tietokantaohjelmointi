package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.TyosuorituksenTuntityo;
import group32.tikoht.service.TyosuorituksenTuntityoService;

@RequestMapping("api/v1/tyosuorituksentuntityo")
@RestController
public class TyosuorituksenTuntityoController {

    private final TyosuorituksenTuntityoService tyotService;

    @Autowired
    public TyosuorituksenTuntityoController(TyosuorituksenTuntityoService tyotService) {
        this.tyotService = tyotService;
    }

    @CrossOrigin
    @PostMapping
    public void addTyosuorituksenTuntityo(@Valid @NotNull @RequestBody TyosuorituksenTuntityo tyot) {
        tyotService.addTyosuorituksenTuntityo(tyot);
    }

    @CrossOrigin
    @GetMapping
    public List<TyosuorituksenTuntityo> getAllTyosuorituksenTuntityo() {
        return tyotService.getAllTyosuorituksenTuntityo();
    }

    @CrossOrigin
    @GetMapping(path = "{suoritusID}")
    public TyosuorituksenTuntityo getTyosuorituksenTuntityoById(@PathVariable("suoritusID") Integer suoritusID) {
        return tyotService.getTyosuorituksenTuntityoById(suoritusID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{suoritusID}")
    public void deleteTyosuorituksenTuntityoById(@PathVariable("suoritusID") Integer suoritusID) {
        tyotService.deleteTyosuorituksenTuntityo(suoritusID);
    }

    @CrossOrigin
    @PutMapping(path = "{suoritusID}")
    public void updateTyosuorituksenTuntityo(@PathVariable("suoritusID") Integer suoritusID,@Valid @NotNull @RequestBody TyosuorituksenTuntityo tyotToUpdate) {
        tyotService.updateTyosuorituksenTuntityo(suoritusID, tyotToUpdate);
    }
}
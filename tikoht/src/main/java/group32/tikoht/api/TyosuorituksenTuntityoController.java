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

    @PostMapping
    public void addTyosuorituksenTuntityo(@Valid @NotNull @RequestBody TyosuorituksenTuntityo tyot) {
        tyotService.addTyosuorituksenTuntityo(tyot);
    }

    @GetMapping
    public List<TyosuorituksenTuntityo> getAllTyosuorituksenTuntityo() {
        return tyotService.getAllTyosuorituksenTuntityo();
    }

    @GetMapping(path = "{suoritusID}")
    public TyosuorituksenTuntityo getTyosuorituksenTuntityoById(@PathVariable("suoritusID") Integer suoritusID) {
        return tyotService.getTyosuorituksenTuntityoById(suoritusID)
                .orElse(null);
    }

    @DeleteMapping(path = "{suoritusID}")
    public void deleteTyosuorituksenTuntityoById(@PathVariable("suoritusID") Integer suoritusID) {
        tyotService.deleteTyosuorituksenTuntityo(suoritusID);
    }

    @PutMapping(path = "{suoritusID}")
    public void updateTyosuorituksenTuntityo(@PathVariable("suoritusID") Integer suoritusID,@Valid @NotNull @RequestBody TyosuorituksenTuntityo tyotToUpdate) {
        tyotService.updateTyosuorituksenTuntityo(suoritusID, tyotToUpdate);
    }
}
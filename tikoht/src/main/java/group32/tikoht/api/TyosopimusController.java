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

import group32.tikoht.model.Tyosopimus;
import group32.tikoht.service.TyosopimusService;

@RequestMapping("api/v1/tyosopimus")
@RestController
public class TyosopimusController {

    private final TyosopimusService tyosopimusService;

    @Autowired
    public TyosopimusController(TyosopimusService tyosopimusService) {
        this.tyosopimusService = tyosopimusService;
    }

    @PostMapping
    public void addTyosopimus(@Valid @NotNull @RequestBody Tyosopimus tyosopimus) {
        tyosopimusService.addTyosopimus(tyosopimus);
    }

    @GetMapping
    public List<Tyosopimus> getAllTyosopimus() {
        return tyosopimusService.getAllTyosopimus();
    }

    @GetMapping(path = "{sopimusID}")
    public Tyosopimus getTyosopimusById(@PathVariable("sopimusID") Integer sopimusID) {
        return tyosopimusService.getTyosopimusById(sopimusID)
                .orElse(null);
    }

    @DeleteMapping(path = "{sopimusID}")
    public void deleteTyosopimusById(@PathVariable("sopimusID") Integer sopimusID) {
        tyosopimusService.deleteTyosopimus(sopimusID);
    }

    @PutMapping(path = "{sopimusID}")
    public void updateTyosopimus(@PathVariable("sopimusID") Integer sopimusID,@Valid @NotNull @RequestBody Tyosopimus tyosopimusToUpdate) {
        tyosopimusService.updateTyosopimus(sopimusID, tyosopimusToUpdate);
    }
}
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

import group32.tikoht.model.Tuntityo;
import group32.tikoht.service.TuntityoService;

@RequestMapping("api/v1/tuntityo")
@RestController
public class TuntityoController {

    private final TuntityoService tuntityoService;

    @Autowired
    public TuntityoController(TuntityoService tuntityoService) {
        this.tuntityoService = tuntityoService;
    }

    @PostMapping
    public void addTuntityo(@Valid @NotNull @RequestBody Tuntityo tuntityo) {
        tuntityoService.addTuntityo(tuntityo);
    }

    @GetMapping
    public List<Tuntityo> getAllTuntityo() {
        return tuntityoService.getAllTuntityo();
    }

    @GetMapping(path = "{tyonTyyppi}")
    public Tuntityo getTuntityoById(@PathVariable("tyonTyyppi") String tyonTyyppi) {
        return tuntityoService.getTuntityoById(tyonTyyppi)
                .orElse(null);
    }

    @DeleteMapping(path = "{tyonTyyppi}")
    public void deleteTuntityoById(@PathVariable("tyonTyyppi") String tyonTyyppi) {
        tuntityoService.deleteTuntityo(tyonTyyppi);
    }

    @PutMapping(path = "{tyonTyyppi}")
    public void updateTuntityo(@PathVariable("tyonTyyppi") String tyonTyyppi,@Valid @NotNull @RequestBody Tuntityo tuntityoToUpdate) {
        tuntityoService.updateTuntityo(tyonTyyppi, tuntityoToUpdate);
    }
}
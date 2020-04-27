package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Asiakas;
import group32.tikoht.service.AsiakasService;

@RequestMapping("api/v1/asiakas")
@RestController
public class AsiakasController {

    private final AsiakasService asiakasService;

    @Autowired
    public AsiakasController(AsiakasService asiakasService) {
        this.asiakasService = asiakasService;
    }

    @CrossOrigin
    @PostMapping
    public void addAsiakas(@Valid @NotNull @RequestBody Asiakas asiakas) {
        asiakasService.addAsiakas(asiakas);
    }

    @CrossOrigin
    @GetMapping
    public List<Asiakas> getAllAsiakas() {
        return asiakasService.getAllAsiakas();
    }

    @CrossOrigin
    @GetMapping(path = "{asiakasID}")
    public Asiakas getAsiakasById(@PathVariable("asiakasID") Integer asiakasID) {
        return asiakasService.getAsiakasById(asiakasID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{asiakasID}")
    public void deleteAsiakasById(@PathVariable("asiakasID") Integer asiakasID) {
        asiakasService.deleteAsiakas(asiakasID);
    }

    @CrossOrigin
    @PutMapping(path = "{asiakasID}")
    public void updateAsiakas(@PathVariable("asiakasID") Integer asiakasID,@Valid @NotNull @RequestBody Asiakas asiakasToUpdate) {
        asiakasService.updateAsiakas(asiakasID, asiakasToUpdate);
    }
}
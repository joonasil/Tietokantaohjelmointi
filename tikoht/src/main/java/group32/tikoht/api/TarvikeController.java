package group32.tikoht.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import group32.tikoht.model.Tarvike;
import group32.tikoht.service.TarvikeService;

@RequestMapping("api/v1/tarvike")
@RestController
public class TarvikeController {

    private final TarvikeService tarvikeService;

    @Autowired
    public TarvikeController(TarvikeService tarvikeService) {
        this.tarvikeService = tarvikeService;
    }

    @CrossOrigin
    @PostMapping
    public void addTarvike(@Valid @NotNull @RequestBody Tarvike tarvike) {
        tarvikeService.addTarvike(tarvike);
    }

    @CrossOrigin
    @GetMapping
    public List<Tarvike> getAllTarvike() {
        return tarvikeService.getAllTarvike();
    }

    @CrossOrigin
    @GetMapping(path = "{tarvikeID}")
    public Tarvike getTarvikeById(@PathVariable("tarvikeID") Integer tarvikeID) {
        return tarvikeService.getTarvikeById(tarvikeID)
                .orElse(null);
    }

    @CrossOrigin
    @DeleteMapping(path = "{tarvikeID}")
    public void deleteTarvikeById(@PathVariable("tarvikeID") Integer tarvikeID) {
        tarvikeService.deleteTarvike(tarvikeID);
    }

    @CrossOrigin
    @PutMapping(path = "{tarvikeID}")
    public void updateTarvike(@PathVariable("tarvikeID") Integer tarvikeID,@Valid @NotNull @RequestBody Tarvike tarvikeToUpdate) {
        tarvikeService.updateTarvike(tarvikeID, tarvikeToUpdate);
    }
}
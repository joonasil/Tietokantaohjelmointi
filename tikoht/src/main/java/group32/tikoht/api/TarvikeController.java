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

    @PostMapping
    public void addTarvike(@Valid @NotNull @RequestBody Tarvike tarvike) {
        tarvikeService.addTarvike(tarvike);
    }

    @GetMapping
    public List<Tarvike> getAllTarvike() {
        return tarvikeService.getAllTarvike();
    }

    @GetMapping(path = "{tarvikeID}")
    public Tarvike getTarvikeById(@PathVariable("tarvikeID") Integer tarvikeID) {
        return tarvikeService.getTarvikeById(tarvikeID)
                .orElse(null);
    }

    @DeleteMapping(path = "{tarvikeID}")
    public void deleteTarvikeById(@PathVariable("tarvikeID") Integer tarvikeID) {
        tarvikeService.deleteTarvike(tarvikeID);
    }

    @PutMapping(path = "{tarvikeID}")
    public void updateTarvike(@PathVariable("tarvikeID") Integer tarvikeID,@Valid @NotNull @RequestBody Tarvike tarvikeToUpdate) {
        tarvikeService.updateTarvike(tarvikeID, tarvikeToUpdate);
    }
}
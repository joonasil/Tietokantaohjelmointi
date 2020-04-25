package group32.tikoht.api;

import group32.tikoht.model.*;
import group32.tikoht.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/raport")
@RestController
public class RaportController {

    private final AsiakasService asiakasService;
    private final LaskuService laskuService;
    private final TarvikeService tarvikeService;
    private final TarvikeluetteloService tarvikeluetteloService;
    private final TuntityoService tuntityoService;
    private final TyokohdeService tyokohdeService;
    private final TyosopimusService tyosopimusService;
    private final TyosuorituksenTuntityoService tyosuorituksenTuntityoService;
    private final TyosuoritusService tyosuoritusService;

    public RaportController(AsiakasService asiakasService, LaskuService laskuService, TarvikeService tarvikeService, TarvikeluetteloService tarvikeluetteloService, TuntityoService tuntityoService, TyokohdeService tyokohdeService, TyosopimusService tyosopimusService, TyosuorituksenTuntityoService tyosuorituksenTuntityoService, TyosuoritusService tyosuoritusService) {
        this.asiakasService = asiakasService;
        this.laskuService = laskuService;
        this.tarvikeService = tarvikeService;
        this.tarvikeluetteloService = tarvikeluetteloService;
        this.tuntityoService = tuntityoService;
        this.tyokohdeService = tyokohdeService;
        this.tyosopimusService = tyosopimusService;
        this.tyosuorituksenTuntityoService = tyosuorituksenTuntityoService;
        this.tyosuoritusService = tyosuoritusService;
    }




}
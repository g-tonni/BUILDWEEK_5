package team2.BUILDWEEK_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team2.BUILDWEEK_5.entities.Fattura;
import team2.BUILDWEEK_5.entities.StatoFattura;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.FattureDTO;
import team2.BUILDWEEK_5.payloads.StatoFattureDTO;
import team2.BUILDWEEK_5.services.FattureService;
import team2.BUILDWEEK_5.services.StatoFattureService;

import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FattureController {

    private final FattureService fattureService;
    private final StatoFattureService statoFattureService;

    @Autowired
    public FattureController(FattureService fattureService, StatoFattureService statoFattureService) {
        this.fattureService = fattureService;
        this.statoFattureService = statoFattureService;
    }

    @PostMapping
    public Fattura createFattura(@RequestBody @Validated FattureDTO payload, BindingResult validation) {

        if (validation.hasErrors()) {
            List<String> errorsList = validation.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsList);

        } else {
            return this.fattureService.saveFattura(payload);
        }
    }

    @GetMapping
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "idCliente") String orderBy,
                                 @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.fattureService.findAllFatture(page, size, orderBy, sortCriteria);
    }

    @PostMapping("/stati")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public StatoFattura createStatoFattura(@RequestBody @Validated StatoFattureDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errorsList = validation.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsList);
        } else {
            return this.statoFattureService.saveStatoFattura(payload);
        }
    }

//    @DeleteMapping()
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    public void
}

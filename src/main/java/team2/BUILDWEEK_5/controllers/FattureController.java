package team2.BUILDWEEK_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team2.BUILDWEEK_5.entities.Fattura;
import team2.BUILDWEEK_5.entities.StatoFattura;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.CambiaStatoFatturaDTO;
import team2.BUILDWEEK_5.payloads.FattureDTO;
import team2.BUILDWEEK_5.payloads.StatoFattureDTO;
import team2.BUILDWEEK_5.services.FattureService;
import team2.BUILDWEEK_5.services.StatoFattureService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/{idFattura}/elimina")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFatture(@PathVariable UUID idFattura) {
        this.fattureService.findByIdAndDelete(idFattura);
    }

    @PatchMapping("/{idFattura}/cambiaStato")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Fattura cambiaStatoFattura(@PathVariable UUID idFattura, @RequestBody @Validated CambiaStatoFatturaDTO cambiaStatoFatturaDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errorsList = validation.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsList);
        } else {
            return this.fattureService.findByIdAndChangeState(idFattura, cambiaStatoFatturaDTO);
        }
    }

    @GetMapping("/search")
    public Page<Fattura> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "cliente") String orderBy,
            @RequestParam(defaultValue = "asc") String sortCriteria,
            @RequestParam(required = false) UUID idCliente,
            @RequestParam(required = false) String statoFattura,
            @RequestParam(required = false) LocalDate dataEmissione,
            @RequestParam(required = false) Integer annoEmissione,
            @RequestParam(required = false) Integer minImporto,
            @RequestParam(required = false) Integer maxImporto
    ) {
        
        return fattureService.filtraFatture(page, size, orderBy, sortCriteria, idCliente, statoFattura, dataEmissione, annoEmissione, minImporto, maxImporto);
    }
}

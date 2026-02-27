package team2.BUILDWEEK_5.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team2.BUILDWEEK_5.entities.Indirizzo;
import team2.BUILDWEEK_5.payloads.IndirizzoDTO;
import team2.BUILDWEEK_5.services.IndirizziService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/indirizzi")
public class IndirizziController {

    private final IndirizziService indirizziService;

    @Autowired
    public IndirizziController(IndirizziService indirizziService) {
        this.indirizziService = indirizziService;
    }

    // GET ALL
    @GetMapping
    public List<Indirizzo> getAll() {
        return indirizziService.findAllIndirizzi();
    }

    // GET PAGINATO
    @GetMapping("/page")
    public Page<Indirizzo> getAllPaged(@PageableDefault(size = 10) Pageable pageable) {
        return indirizziService.findAllIndirizziPaged(pageable);
    }

    // GET FILTRATO PAGINATO
    @GetMapping("/page/filter")
    public Page<Indirizzo> getFiltered(
            @RequestParam(required = false) String localita,
            @RequestParam(required = false) String cap,
            @RequestParam(required = false) UUID idComune,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return indirizziService.findIndirizziFiltered(localita, cap, idComune, pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Indirizzo getById(@PathVariable UUID id) {
        return indirizziService.findById(id);
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo save(@RequestBody @Valid IndirizzoDTO body) {
        return indirizziService.saveIndirizzo(body);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Indirizzo update(@PathVariable UUID id, @RequestBody @Valid IndirizzoDTO body) {
        return indirizziService.findByIdAndUpdate(id, body);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        indirizziService.findByIdAndDelete(id);
    }
}
package team2.BUILDWEEK_5.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 1) GET ALL
    @GetMapping
    public List<Indirizzo> getAll() {
        return indirizziService.findAllIndirizzi();
    }

    // 2) GET BY ID
    @GetMapping("/{id}")
    public Indirizzo getById(@PathVariable UUID id) {
        return indirizziService.findById(id);
    }

    // 3) CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo save(@RequestBody @Valid IndirizzoDTO body) {
        return indirizziService.saveIndirizzo(body);
    }

    // 4) UPDATE
    @PutMapping("/{id}")
    public Indirizzo update(@PathVariable UUID id, @RequestBody @Valid IndirizzoDTO body) {
        return indirizziService.findByIdAndUpdate(id, body);
    }

    // 5) DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        indirizziService.findByIdAndDelete(id);
    }
}
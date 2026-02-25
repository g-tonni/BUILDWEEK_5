package team2.BUILDWEEK_5.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.entities.Contatto;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.ClientiDTO;
import team2.BUILDWEEK_5.payloads.ContattoDTO;
import team2.BUILDWEEK_5.services.ClientiService;
import team2.BUILDWEEK_5.services.ContattoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    private final ClientiService clientiService;
    private final ContattoService contattoService;

    public ClientiController(ClientiService clientiService, ContattoService contattoService) {
        this.clientiService = clientiService;
        this.contattoService = contattoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody @Validated ClientiDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.clientiService.save(payload);
        }
    }

    @GetMapping
    public Page<Cliente> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "email") String orderBy,
                                 @RequestParam(defaultValue = "asc") String sortCriteria) {

        return this.clientiService.findAll(page, size, orderBy, sortCriteria);
    }

    @GetMapping("/{id}")
    public Cliente findById(UUID id) {
        return this.clientiService.findById(id);
    }

    @DeleteMapping
    public void findByIdAndDelete(UUID id) {
        this.clientiService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    public Cliente updateProfile(@PathVariable UUID id, @RequestBody @Validated ClientiDTO payload) {
        return this.clientiService.update(id, payload);
    }

    @PostMapping("/contatti")
    @ResponseStatus(HttpStatus.CREATED)
    public Contatto createContatto(@RequestBody @Validated ContattoDTO contattoDTO, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.contattoService.save(contattoDTO);
        }
    }
}

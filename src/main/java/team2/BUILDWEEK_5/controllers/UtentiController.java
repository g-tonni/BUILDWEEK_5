package team2.BUILDWEEK_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team2.BUILDWEEK_5.entities.Ruolo;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.RuoloDTO;
import team2.BUILDWEEK_5.services.UtentiService;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtentiController {
    private final UtentiService utentiService;

    @Autowired
    public UtentiController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    @PostMapping("/ruoli")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Ruolo saveRuolo(@RequestBody @Validated RuoloDTO ruoloDTO, BindingResult validationResults) {

        if (validationResults.hasErrors()) {

            List<String> errorsList = validationResults.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            throw new ValidationException(errorsList);
        } else {
            return this.utentiService.saveRuolo(ruoloDTO);
        }
    }
}

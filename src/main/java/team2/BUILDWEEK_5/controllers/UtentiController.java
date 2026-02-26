package team2.BUILDWEEK_5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team2.BUILDWEEK_5.entities.Ruolo;
import team2.BUILDWEEK_5.entities.Utente;
import team2.BUILDWEEK_5.exceptions.ValidationException;
import team2.BUILDWEEK_5.payloads.RuoloDTO;
import team2.BUILDWEEK_5.payloads.UpdateUtenteDTO;
import team2.BUILDWEEK_5.services.UtentiService;

import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/{idUtente}/elimina")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUtente(@PathVariable UUID idUtente) {
        this.utentiService.deleteUtente(idUtente);
    }

    @PutMapping("/me/modifica")
    public Utente updateUtente(@AuthenticationPrincipal Utente utente, @RequestBody @Validated UpdateUtenteDTO body, BindingResult validationResults) {
        if (validationResults.hasErrors()) {

            List<String> errorsList = validationResults.getFieldErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

            throw new ValidationException(errorsList);
        } else {
            return this.utentiService.updateUtente(utente, body);
        }
    }

    @PatchMapping("/me/avatar")
    public Utente updateAvatarUtente(@AuthenticationPrincipal Utente utente, @RequestParam("foto_profilo") MultipartFile file) {
        return this.utentiService.uploadFotoProfilo(utente.getIdUtente(), file);
    }
}

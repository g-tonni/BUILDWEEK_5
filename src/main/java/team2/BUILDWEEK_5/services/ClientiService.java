package team2.BUILDWEEK_5.services;

import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.repositories.ClientiRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientiService {

    private final ClientiRepository clientiRepository;


    Optional<Cliente> findById  (UUID idCliente){

        return this.clientiRepository.findById(idCliente).orElseThrow(() -> new NotFoundException("Cliente non trovato. Id: " + idCliente ))
    }
}

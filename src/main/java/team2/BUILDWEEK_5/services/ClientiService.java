package team2.BUILDWEEK_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.exceptions.NotFoundException;
import team2.BUILDWEEK_5.repositories.ClientiRepository;

import java.util.UUID;

@Service

public class ClientiService {

    private final ClientiRepository clientiRepository;

    @Autowired
    public ClientiService(ClientiRepository clientiRepository) {
        this.clientiRepository = clientiRepository;
    }

    public Cliente findById(UUID idCliente) {
        return this.clientiRepository.findById(idCliente).orElseThrow(() -> new NotFoundException("Cliente non trovato. Id: " + idCliente));
    }
}

package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findBySedeLegale(String sedeLegale);

    Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

    Optional<Cliente> findByDataUltimoContatto(LocalDate dataInserimento);

    Optional<Cliente> findByTelefono(String telefono);

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findAllByFatturatoAnnuale(String fatturatoAnnuale);

    List<Cliente> findAllByDataInserimento(LocalDate dataInserimento);

    Optional<Cliente> findByPartitaIva(String partitaIva);

    List<Cliente> findByFatturatoAnnualeGreaterThan(Double minFatturato);

    List<Cliente> findByFatturatoAnnualeLessThan(Double minFatturato);

    List<Cliente> findByFatturatoAnnualeBetween(Double minFatturato, Double maxFatturato);
    
}

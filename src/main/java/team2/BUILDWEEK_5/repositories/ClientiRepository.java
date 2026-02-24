package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Cliente;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findBySedeLegale(String sedeLegale);

    Optional<Cliente> findByDataInserimento(LocalDate dataInserimento);

    Optional<Cliente> findByDataUltimoContatto(LocalDate dataInserimento);
}

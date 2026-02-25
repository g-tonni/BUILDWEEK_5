package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FattureRepository extends JpaRepository<Fattura, UUID> {

    List<Fattura> findByClienteAndDataFattura(UUID idCliente, LocalDate dataFattura);

    List<Fattura> findByCliente(UUID idCliente);
}

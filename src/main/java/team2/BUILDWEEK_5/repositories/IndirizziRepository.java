package team2.BUILDWEEK_5.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Comune;
import team2.BUILDWEEK_5.entities.Indirizzo;

import java.util.UUID;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzo, UUID> {

    boolean existsByViaAndCivicoAndComune(String via, String civico, Comune comune);

    Page<Indirizzo> findByLocalitaIgnoreCase(String localita, Pageable pageable);

    Page<Indirizzo> findByCap(String cap, Pageable pageable);

    Page<Indirizzo> findByComune_IdComune(UUID idComune, Pageable pageable);
}


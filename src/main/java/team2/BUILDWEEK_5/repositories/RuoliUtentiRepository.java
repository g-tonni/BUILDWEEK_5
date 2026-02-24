package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.RuoloUtente;

import java.util.UUID;

@Repository
public interface RuoliUtentiRepository extends JpaRepository<RuoloUtente, UUID> {
}

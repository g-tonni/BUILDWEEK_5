package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Contatto;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContattoRepository extends JpaRepository<Contatto, UUID> {
    Optional<Contatto> findByEmail(String email);

    Optional<Contatto> findByTelefono(String telefono);
}

package team2.BUILDWEEK_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.BUILDWEEK_5.entities.Provincia;

import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Provincia, String> {
    Optional<Provincia> findByNome(String nome);
}

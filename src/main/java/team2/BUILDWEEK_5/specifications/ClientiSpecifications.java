package team2.BUILDWEEK_5.specifications;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.entities.Comune;
import team2.BUILDWEEK_5.entities.Indirizzo;
import team2.BUILDWEEK_5.entities.Provincia;

import java.time.LocalDate;

public class ClientiSpecifications {

    public static Specification<Cliente> fatturatoGreaterThanOrEqualTo(Double minFatturato) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("fatturatoAnnuale"), minFatturato
                );
    }

    public static Specification<Cliente> fatturatoLessThanOrEqualTo(Double maxFatturato) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("fatturatoAnnuale"), maxFatturato
                );
    }

    public static Specification<Cliente> dataDiInserimentoEqualsTo(LocalDate dataInserimento) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("dataInserimento"), dataInserimento
                );
    }

    public static Specification<Cliente> dataUltimoContattoEqualsTo(LocalDate dataUltimoContatto) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("dataUltimoContatto"), dataUltimoContatto
                );
    }

    public static Specification<Cliente> partialNameEqualsTo(String partialName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeCliente")), "%" + partialName.toLowerCase() + "%");
    }

    public static Specification<Cliente> provinciaEquals(String nomeProvincia) {

        return (root, query, cb) -> {

            query.distinct(true);

            Join<Cliente, Indirizzo> sedeLegale = root.join("sedeLegale");
            Join<Indirizzo, Comune> comune = sedeLegale.join("comune");
            Join<Comune, Provincia> provincia = comune.join("provincia");

            return cb.equal(
                    cb.lower(provincia.get("nome")),
                    nomeProvincia.toLowerCase()
            );
        };
    }
}

package team2.BUILDWEEK_5.specifications;

import org.springframework.data.jpa.domain.Specification;
import team2.BUILDWEEK_5.entities.Cliente;

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
}

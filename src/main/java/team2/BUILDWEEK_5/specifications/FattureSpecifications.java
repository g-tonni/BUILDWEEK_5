package team2.BUILDWEEK_5.specifications;


import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import team2.BUILDWEEK_5.entities.Cliente;
import team2.BUILDWEEK_5.entities.Fattura;
import team2.BUILDWEEK_5.entities.StatoFattura;
import team2.BUILDWEEK_5.services.ClientiService;
import team2.BUILDWEEK_5.services.StatoFattureService;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class FattureSpecifications {

    private final ClientiService clientiService;
    private final StatoFattureService statoFattureService;

    public Specification<Fattura> filtraFatturePerCliente(UUID idCliente) {

        Cliente found = this.clientiService.findById(idCliente);

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("cliente"), found
                );
    }

    public Specification<Fattura> filtraFatturePerStato(String statoFattura) {

        StatoFattura found = this.statoFattureService.findStatoFatturaById(statoFattura);

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("statoFattura"), found
                );
    }

    public Specification<Fattura> dataDiEmissioneEqualsTo(LocalDate dataEmissione) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("dataFattura"), dataEmissione
                );
    }

    public Specification<Fattura> annoDiEmissioneEqualsTo(Integer annoEmissione) {
        LocalDate start = LocalDate.of(annoEmissione, 1, 1);
        LocalDate end = LocalDate.of(annoEmissione, 12, 31);

        return (root, query, cb) ->
                cb.between(root.get("dataFattura"), start, end);
    }

    public Specification<Fattura> importoFatturaBetween(Integer minImporto, Integer maxImporto) {
        return (root, query, cb) ->
                cb.between(root.get("importoFattura"), minImporto, maxImporto);
    }

    public Specification<Fattura> importoFatturaGreaterThanOrEqualsTo(Integer minImporto) {
        return (root, query, cb) ->
                cb.greaterThanOrEqualTo(root.get("importoFattura"), minImporto);
    }

    public Specification<Fattura> importoFatturaLessThanOrEqualsTo(Integer maxImporto) {
        return (root, query, cb) ->
                cb.lessThanOrEqualTo(root.get("importoFattura"), maxImporto);
    }


}

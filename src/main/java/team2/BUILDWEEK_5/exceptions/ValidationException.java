package team2.BUILDWEEK_5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private List<String> errorsList;

    public ValidationException(List<String> errorsList) {
        super("Errori nella validazione");

        this.errorsList = errorsList;
    }
}

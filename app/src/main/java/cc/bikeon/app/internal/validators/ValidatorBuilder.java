package cc.bikeon.app.internal.validators;

import java.util.Collection;
import java.util.HashSet;

/**
 * Builder for make easier to create composed validator
 * @param <T> type of validation
 */
public class ValidatorBuilder<T> {
    private Collection<Validation<T>> listOfValidations =
            new HashSet<Validation<T>>();

    public ValidatorBuilder  addValidation(Validation<T> validation) {
        listOfValidations.add(validation);
        return this;
    }

    public Validator<T> build() {
        return new Validator<T>(listOfValidations);
    }
}

package cc.bikeon.app.internal.validators;

import java.util.Collection;

/**
 * Responsible for validate a given type <T> with a list of validations
 * @param <T> type of validations
 * Created by cristianoliveira on 18/08/15.
 */
public class Validator<T> {

    private Collection<Validation<T>> validations;

    public Validator(Collection<Validation<T>> validations) {
        this.validations = validations;
    }

    /**
     * Validates a given Type<T> against a set of validations. When at least one fail
     * it returns the error message.
     * @param itemToValidate to be validate
     * @return Error Message or Null if has no errors
     */
    public String validate(T itemToValidate) {
        for(Validation<T> validation : validations) {
            String error = validation.validate(itemToValidate);
            if(error != null){
                return error;
            }
        }
        return null;
    }


}

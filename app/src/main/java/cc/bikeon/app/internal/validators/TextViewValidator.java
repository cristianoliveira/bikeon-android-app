package cc.bikeon.app.internal.validators;

import android.widget.TextView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Responsible for validate a given {@link android.widget.TextView} with a list of validations
 * Created by cristianoliveira on 18/08/15.
 */
public class TextViewValidator {

    private Collection<Validation<TextView>> validations;

    public TextViewValidator(Collection<Validation<TextView>> validations) {
        this.validations = validations;
    }

    /**
     * Validates a given TextView against a set of validations. When at least one fail
     * it returns the error message.
     * @param textView to be validate
     * @return Error Message
     */
    public String validate(TextView textView) {
        for(Validation<TextView> validation : validations) {
            String error = validation.validate(textView);
            if(error != null){
                return error;
            }
        }
        return null;
    }

    public static class Builder {
        private Collection<Validation<TextView>> listOfValidations =
                new HashSet<Validation<TextView>>();

        public Builder addValidation(Validation<TextView> validation) {
            listOfValidations.add(validation);
            return this;
        }

        public TextViewValidator build() {
            return new TextViewValidator(listOfValidations);
        }
    }

}

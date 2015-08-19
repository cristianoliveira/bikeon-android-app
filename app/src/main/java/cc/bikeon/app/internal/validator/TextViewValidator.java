package cc.bikeon.app.internal.validator;

import android.widget.TextView;

import java.util.Collection;
import java.util.HashSet;

/**
 * Responsible for validate a given {@link android.widget.TextView} with a list of validations
 * Created by cristianoliveira on 18/08/15.
 */
public class TextViewValidator {

    private Collection<TextViewValidation> validations;

    public TextViewValidator(Collection<TextViewValidation> validations) {
        this.validations = validations;
    }

    /**
     * Validates a given TextView against a set of validations. When at least one fail
     * it returns the error message.
     * @param textView to be validate
     * @return Error Message
     */
    public String validate(TextView textView) {
        for(TextViewValidation validation : validations) {
            String error = validation.validate(textView);
            if(error != null){
                return error;
            }
        }
        return null;
    }

    public static class Builder {
        private Collection<TextViewValidation> listOfValidations = new HashSet<TextViewValidation>();

        public Builder addValidation(TextViewValidation validation) {
            listOfValidations.add(validation);
            return this;
        }

        public TextViewValidator build() {
            return new TextViewValidator(listOfValidations);
        }
    }

}

package cc.bikeon.app.internal.validators;

import android.widget.TextView;

/**
 * Responsible for validate empty text views.
 * Created by cristianoliveira on 18/08/15.
 */
public class EmptyTextViewValidation implements Validation<TextView> {

    private String errorMessage;

    public EmptyTextViewValidation(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String validate(TextView textView) {
        if (textView.getText().length() == 0) {
            return errorMessage;
        } else {
            return null;
        }
    }
}

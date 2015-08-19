package cc.bikeon.app.internal.validator;

import android.widget.TextView;

/**
 * Responsible for validate empty text views.
 * Created by cristianoliveira on 18/08/15.
 */
public class EmptyTextViewValidation implements TextViewValidation {

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

package cc.bikeon.app.internal.validator;

import android.widget.TextView;

/**
 * Interface for validators
 *
 * @param <T> type of object to validate
 *
 * Created by cristianoliveira on 18/08/15.
 */
public interface Validation<T> {
    String validate(T textView);
}

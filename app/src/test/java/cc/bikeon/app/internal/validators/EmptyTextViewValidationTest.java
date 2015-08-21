package cc.bikeon.app.internal.validators;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link EmptyTextViewValidation}
 * Created by cristianoliveira on 18/08/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EmptyTextViewValidationTest {

    String errorMessage = "Some error Message";
    EmptyTextViewValidation emptyTextViewValidation;

    @Before
    public void setUp() {
        emptyTextViewValidation = new EmptyTextViewValidation(errorMessage);
    }

    @Test
    public void whenTextViewIsEmptyItShouldReturnErrorMessage() {
        // given
        TextView textView = mock(TextView.class);
        given(textView.getText()).willReturn("");

        // when
        String result = emptyTextViewValidation.validate(textView);

        // then
        assertEquals(errorMessage, result);
    }

    @Test
    public void whenTextViewIsNotEmptyItShouldReturnNull() {
        // given
        TextView textView = mock(TextView.class);
        given(textView.getText()).willReturn("some text");

        // when
        String result = emptyTextViewValidation.validate(textView);

        // then
        assertNull(result);
    }


}
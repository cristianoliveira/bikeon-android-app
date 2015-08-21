package cc.bikeon.app.internal.validators;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for class {@link Validator}
 * Created by cristianoliveira on 18/08/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class TextViewValidatorTest {

    Validator validator;

    @Test
    public void whenListOfValidationIsNullItShouldReturnNull() {
        // given
        TextView textView = mock(TextView.class);
        given(textView.getText()).willReturn("");
        Set<Validation<TextView>> validations = new HashSet<Validation<TextView>>();
        validator = new Validator(validations);

        // when
        String result = validator.validate(textView);

        // then
        assertNull(result);
    }


    @Test
    public void whenListReceiveSetOfValidationAndAtLeastOneFailItShouldReturnErrorMessage() {
        // given
        String expected = "Some error message";
        TextView textView = mock(TextView.class);

        Validation notFailValidate = mock(Validation.class);
        given(notFailValidate.validate(textView)).willReturn(null);

        Validation failValidate = mock(Validation.class);
        given(failValidate.validate(textView)).willReturn(expected);


        Set<Validation<TextView>> validations = new HashSet<Validation<TextView>>();
        validations.add(failValidate);
        validations.add(notFailValidate);

        validator = new Validator(validations);

        // when
        String result = validator.validate(textView);

        // then
        assertEquals(expected, result);
    }


    @Test
    public void whenReceiveSetOfValidationAndAnyoneFailItShouldReturnNull() {
        // given
        TextView textView = mock(TextView.class);

        Validation<TextView> notFailValidate = mock(Validation.class);
        given(notFailValidate.validate(textView)).willReturn(null);

        Validation<TextView> notFailValidate2 = mock(Validation.class);
        given(notFailValidate2.validate(textView)).willReturn(null);


        Collection<Validation<TextView>> validations = new HashSet<Validation<TextView>>();
        validations.add(notFailValidate);
        validations.add(notFailValidate2);

        validator = new Validator(validations);

        // when
        String result = validator.validate(textView);

        // then
        assertNull(result);
    }
}
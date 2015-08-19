package cc.bikeon.app.internal.validator;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for class {@link TextViewValidator}
 * Created by cristianoliveira on 18/08/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class TextViewValidatorTest {

    TextViewValidator validator;

    @Test
    public void whenListOfValidationIsNullItShouldNotValidate() {
        // given
        TextView textView = mock(TextView.class);
        given(textView.getText()).willReturn("");
        Set<Validation> validations = new HashSet<Validation>();
        validator = new TextViewValidator(validations);

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


        Set<Validation> validations = new HashSet<Validation>();
        validations.add(failValidate);
        validations.add(notFailValidate);

        validator = new TextViewValidator(validations);

        // when
        String result = validator.validate(textView);

        // then
        assertEquals(expected, result);
    }


    @Test
    public void whenReceiveSetOfValidationAndAnyoneFailItShouldReturnNull() {
        // given
        TextView textView = mock(TextView.class);

        Validation notFailValidate = mock(Validation.class);
        given(notFailValidate.validate(textView)).willReturn(null);

        Validation notFailValidate2 = mock(Validation.class);
        given(notFailValidate2.validate(textView)).willReturn(null);


        Set<Validation> validations = new HashSet<Validation>();
        validations.add(notFailValidate);
        validations.add(notFailValidate2);

        validator = new TextViewValidator(validations);

        // when
        String result = validator.validate(textView);

        // then
        assertNull(result);
    }
}
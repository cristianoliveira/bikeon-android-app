package cc.bikeon.app.ui.main;

import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;

import cc.bikeon.app.BaseRoboletricTestRunner;
import cc.bikeon.app.internal.validators.Validator;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.util.FragmentTestUtil.startFragment;

/**
 * Tests for {@link LocationFragment}
 * Created by cristianoliveira on 29/08/15.
 */
@RunWith(BaseRoboletricTestRunner.class)
public class LocationFragmentTest {

    LocationFragment.DestinationListener destinationListener;
    LocationFragment locationFragment;

    @Before
    public void setUp() {
        destinationListener =
                mock(LocationFragment.DestinationListener.class);

        locationFragment = new LocationFragment();
        startFragment(locationFragment);

        locationFragment.setDestinationListener(destinationListener);
    }

    @Test(expected = NullPointerException.class)
    public void itShouldRaiseNPEWhenSelectDestinationWithNoListener() {
        // given

        Validator mockedValidator = mock(Validator.class);
        given(mockedValidator.validate(anyObject())).willReturn(null);

        locationFragment.setTextViewValidator(mockedValidator);

        locationFragment.setDestinationListener(null);

        // when.. raise NPE
        locationFragment.btnWhereUGo.callOnClick();

    }

    @Test
    public void itShouldSetErrorOnTextViewWhenValidatorReturnError() {
        // given
        String expected = "Some error message";

        TextView mockedTextView = mock(TextView.class);
        locationFragment.etxWhereYouGo = mockedTextView;

        Validator mockedValidator = mock(Validator.class);
        given(mockedValidator.validate(anyObject())).willReturn(expected);

        locationFragment.setTextViewValidator(mockedValidator);

        // when
        locationFragment.btnWhereUGo.callOnClick();

        // then
        verify(mockedTextView).setError(expected);
    }

    @Test
    public void itShouldSendDestinationToListenerWhenSelected() {
        // given
        String expected = "Any destination";

        TextView mockedTextView = mock(TextView.class);
        given(mockedTextView.getText()).willReturn(expected);
        locationFragment.etxWhereYouGo = mockedTextView;

        Validator mockedValidator = mock(Validator.class);
        given(mockedValidator.validate(anyObject())).willReturn(null);
        locationFragment.setTextViewValidator(mockedValidator);

        // when
        locationFragment.btnWhereUGo.callOnClick();

        // then
        verify(destinationListener).onDestinationSelect(expected);
    }

}
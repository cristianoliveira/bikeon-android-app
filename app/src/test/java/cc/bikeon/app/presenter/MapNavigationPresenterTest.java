package cc.bikeon.app.presenter;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cc.bikeon.app.R;
import cc.bikeon.app.domain.directions.Coordinate;
import cc.bikeon.app.services.rest.directions.DirectionCallback;
import cc.bikeon.app.services.rest.directions.DirectionRequester;
import cc.bikeon.app.views.MapNavigationView;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link  MapNavigationPresenter}
 * Created by cristianoliveira on 22/08/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class MapNavigationPresenterTest {

    @Mock
    MapNavigationView mockedView;
    @Mock
    DirectionRequester mockedDirectionRequester;

    @InjectMocks
    MapNavigationPresenter mapNavigationPresenter;

    @Test
    public void itShouldCallViewErrorWhenRequestDirectionsWithInvalidEncode() throws UnsupportedEncodingException {
        // given
        doThrow(UnsupportedEncodingException.class)
                .when(mockedDirectionRequester).request(anyString(),
                anyString(),
                any(DirectionCallback.class));

        // when
        mapNavigationPresenter.requestDirections();

        // then
        verify(mockedView).showMessageError(R.string.message_error_encode);
    }

    @Test
    public void itShouldNotSetMapRoutesWhenRequestDirectionsHasNoRoute() throws UnsupportedEncodingException {
        // given
        final List<Coordinate> emptyResult = Lists.newArrayList();

        doAnswer(new SuccessAnswer(emptyResult)).when(mockedDirectionRequester).request(anyString(),
                anyString(),
                any(DirectionCallback.class));

        // when
        mapNavigationPresenter.requestDirections();

        // then
        verify(mockedView, never()).setMapRoute(emptyResult);
    }

    @Test
    public void itShouldSetMapRoutesWithResultWhenRequestDirectionsReturnHasRoutes() throws UnsupportedEncodingException {
        // given
        Coordinate coordinate = new Coordinate(1,1);
        final List<Coordinate> result = Lists.newArrayList(coordinate);

        doAnswer(new SuccessAnswer(result)).when(mockedDirectionRequester).request(anyString(),
                anyString(),
                any(DirectionCallback.class));

        // when
        mapNavigationPresenter.requestDirections();

        // then
        verify(mockedView).setMapRoute(result);
    }

    @Test
    public void itShouldShowErrorWhenRequestRequestFail() throws UnsupportedEncodingException {
        // given
        Coordinate coordinate = new Coordinate(1,1);
        final List<Coordinate> result = Lists.newArrayList(coordinate);

        doAnswer(new ErrorAnswer()).when(mockedDirectionRequester).request(anyString(),
                anyString(),
                any(DirectionCallback.class));

        // when
        mapNavigationPresenter.requestDirections();

        // then
        verify(mockedView).showMessageError(R.string.message_error_unavailable_service);
    }

    /** Stub Answers **/
    private class SuccessAnswer implements Answer {

        List<Coordinate> result;

        public SuccessAnswer(List<Coordinate> result) {
            this.result = result;
        }

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            DirectionCallback callback = (DirectionCallback) invocation.getArguments()[2];
            callback.onSuccess(result);
            return null;
        }
    }

    private class ErrorAnswer implements Answer {

        String ERROR_MESSAGE = "Some error";

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            DirectionCallback callback = (DirectionCallback) invocation.getArguments()[2];
            callback.onFailure(ERROR_MESSAGE);
            return null;
        }
    }

}
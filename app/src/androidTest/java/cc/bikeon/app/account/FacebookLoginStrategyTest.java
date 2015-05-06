package cc.bikeon.app.account;

import android.app.Activity;
import android.test.AndroidTestCase;

import com.facebook.Session;
import com.facebook.SessionState;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import cc.bikeon.app.BuildConfig;

import static org.mockito.Mockito.*;

/**
 * Created by cristianoliveira on 05/05/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class FacebookLoginStrategyTest extends AndroidTestCase {


//	@Before
//	public void setUp()
//	{
//
//		mockedActivity = mock(Activity.class);
//
//		openedSession = mock(Session.class);
//		when(openedSession.isOpened()).thenReturn(true);
//
//		closedSession = mock(Session.class);
//		when(closedSession.isOpened()).thenReturn(false);
//
//		sessionState = mock(SessionState.class);
//
//	}

	@Test
	public void testWhenCallbackReturnOpenedSessionItShouldBeSuccess()
	{
		// given
		Session openedSession = mock(Session.class);
		when(openedSession.isOpened()).thenReturn(true);
		StubCallBack callBack = new StubCallBack();
		FacebookSessionCallback facebookSessionCallback = new FacebookSessionCallback(callBack);
		FacebookLoginStrategy facebookLoginStrategy = mock(FacebookLoginStrategy.class);
		when(facebookLoginStrategy.getCallBack(callBack)).thenReturn(facebookSessionCallback);

		// when
		facebookSessionCallback.call(openedSession, null, null);

		// then
		assertTrue(callBack.isSuccess());
	}

	class StubCallBack implements ILoginCallback{

		private boolean isSuccess = false;
		private String errorMessage;

		@Override
		public void onSuccess() {
			isSuccess = true;
		}

		@Override
		public void onError(String messageError) {
      this.errorMessage = messageError;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
	}
}

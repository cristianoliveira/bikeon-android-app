package cc.bikeon.app.account;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import static org.mockito.Mockito.*;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.TokenCachingStrategy;

import cc.bikeon.app.ui.LoginActivity;

/**
 * Created by cristianoliveira on 14/05/15.
 */
public class FacebookSessionCallbackTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public FacebookSessionCallbackTest() {
        super(LoginActivity.class);
    }

    @SmallTest
    public void testWhenSessionOpenedShouldExecuteOnLoginSuccess()
    {
        // given
        Session session = spy(new Session(getActivity()));
        when(session.isOpened()).thenReturn(true);
        ILoginCallback callback = mock(ILoginCallback.class);
        FacebookSessionCallback facebookSessionCallback =
                new FacebookSessionCallback(callback);

        // when
        facebookSessionCallback.call(session, any(SessionState.class), null);

        // then
        //Todo: Create tests        verify(callback, times(1)).onLoginSuccess();
    }
}

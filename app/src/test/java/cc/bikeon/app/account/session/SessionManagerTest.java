package cc.bikeon.app.account.session;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link SessionManager}
 * Created by cristianoliveira on 24/08/15.
 */

public class SessionManagerTest {

    SharedPreferences sharedPreferences;
    SessionManager sessionManager;

    @Before
    public void setUp() {
        sharedPreferences = mock(SharedPreferences.class);
        sessionManager = new SessionManager(sharedPreferences);
    }

    public void itShouldRaiseNPEWhenTrySaveNullSession() {
        // given
        SessionAccount session = null;

        // when... then raise exception
        sessionManager.saveCurrentProvider(session);
    }

    @Test
    public void itShouldSaveSessionProviderAsString() {
        // given
        SessionAccount sessionAccount = new BikeOnSession();
        String expected = sessionAccount.getProvider().toString();

        SharedPreferences.Editor mockedEditor = mock(SharedPreferences.Editor.class);
        given(sharedPreferences.edit()).willReturn(mockedEditor);

        // when
        boolean result = sessionManager.saveCurrentProvider(sessionAccount);

        // then
        verify(mockedEditor).putString(SessionManager.SESSION_PROVIDER, expected);
    }

    @Test
    public void itShouldReturnTrueWhenProviderWasSavedSuccessfully() {
        // given
        SessionAccount sessionAccount = new BikeOnSession();

        SharedPreferences.Editor mockedEditor = mock(SharedPreferences.Editor.class);
        given(sharedPreferences.edit()).willReturn(mockedEditor);
        given(mockedEditor.commit()).willReturn(true);

        // when
        boolean result = sessionManager.saveCurrentProvider(sessionAccount);

        // then
        assertTrue(result);
    }

    @Test
    public void itShouldReturnFalseWhenProviderWasNotSaved() {
        // given
        SessionAccount sessionAccount = new BikeOnSession();

        SharedPreferences.Editor mockedEditor = mock(SharedPreferences.Editor.class);
        given(sharedPreferences.edit()).willReturn(mockedEditor);
        given(mockedEditor.commit()).willReturn(false);

        // when
        boolean result = sessionManager.saveCurrentProvider(sessionAccount);

        // then
        assertFalse(result);
    }

    @Test
    public void itShouldReturnBikeonSessionWhenProviderSavedIsBikeOn() {
        // given
        SessionProvider provider = SessionProvider.BIKEON;

        given(sharedPreferences.getString(SessionManager.SESSION_PROVIDER, null))
                .willReturn(provider.toString());

        // when
        SessionAccount result = sessionManager.getCurrentSession();

        // then
        assertTrue(result instanceof BikeOnSession);
    }

    @Test
    public void itShouldReturnFacebookSessionWhenProviderSavedIsFacebook() {
        // given
        SessionProvider provider = SessionProvider.FACEBOOK;

        given(sharedPreferences.getString(SessionManager.SESSION_PROVIDER, null))
                .willReturn(provider.toString());

        // when
        SessionAccount result = sessionManager.getCurrentSession();

        // then
        assertTrue(result instanceof FacebookSession);
    }


}
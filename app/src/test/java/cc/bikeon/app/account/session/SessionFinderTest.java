package cc.bikeon.app.account.session;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link SessionFinder}
 * Created by cristianoliveira on 24/08/15.
 */
public class SessionFinderTest {

    SessionFinder sessionManager = new SessionFinder();

    @Test(expected = NullPointerException.class)
    public void itShouldRaiseNPEWhenListOfProvidersIsNull() {
        // given
        List<SessionProvider> providers = null;

        // when
        SessionProvider result = sessionManager.getCurrentProvider(providers);

        // then raise exception
    }

    @Test
    public void itShouldReturnWhenListOfProvidersIsEmpty() {
        // given
        List<SessionProvider> providers = Lists.newArrayList();

        // when
        SessionProvider result = sessionManager.getCurrentProvider(providers);

        // then
        assertNull(result);
    }


    @Test
    public void itShouldReturnNullWhenAnyProviderHasActiveSession() {
        // given
        SessionProvider provider = mock(SessionProvider.class);
        given(provider.hasSessionActive()).willReturn(false);

        List<SessionProvider> providers = Lists.newArrayList(provider);

        // when
        SessionProvider result = sessionManager.getCurrentProvider(providers);

        // then
        assertNull(result);
    }

    @Test
    public void itShouldReturnProviderWhenItHasActiveSession() {
        // given
        SessionProvider provider = mock(SessionProvider.class);
        given(provider.hasSessionActive()).willReturn(false);

        SessionProvider expected = mock(SessionProvider.class);
        given(expected.hasSessionActive()).willReturn(true);

        List<SessionProvider> providers = Lists.newArrayList(expected, provider);

        // when
        SessionProvider result = sessionManager.getCurrentProvider(providers);

        // then
        assertEquals(expected, result);
    }

}
package cc.bikeon.app.account;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by cristianoliveira on 04/05/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginRequesterTest extends TestCase{

    @Test
    public void testWhenItReceiveAnStrategySuccessShouldReturnCallback()
    {
        // given
        boolean expected = true;
        StubStrategy strategy = new StubStrategy();
        strategy.setSuccess(expected);
        LoginRequester loginRequester = new LoginRequester();
        loginRequester.setStrategy(strategy);
        StubLoginCallback callback = new StubLoginCallback();

        // when
        loginRequester.requestLogin(callback);

        // then
        assertEquals(callback.isSuccess(), expected);
    }

    @Test
    public void testWhenItReceiveAnStrategySuccessShouldReturnCallbackWhitErrorMessage()
    {
        // given
        String expected = "My error message";
        StubStrategy strategy = new StubStrategy();
        strategy.setSuccess(false);
        strategy.setErrorMessage(expected);
        LoginRequester loginRequester = new LoginRequester();
        loginRequester.setStrategy(strategy);
        StubLoginCallback callback = new StubLoginCallback();


        // when
        loginRequester.requestLogin(callback);

        // then
        assertEquals(callback.getErrorMessage(), expected);
    }

    private class StubStrategy implements LoginStrategy {

        private boolean success;
        private String errorMessage;

        @Override
        public void doLogin(LoginCallback callback) {
            if(success)
            {
                callback.onLoginSuccess();
            }else
            {
                callback.onLoginError(getErrorMessage());
            }

        }

        public void setSuccess(boolean success)
        {
            this.success = success;
        }

        public boolean success()
    {
        return success;
    }

        public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

        public String getErrorMessage()
    {
        return errorMessage;
    }
    }

}

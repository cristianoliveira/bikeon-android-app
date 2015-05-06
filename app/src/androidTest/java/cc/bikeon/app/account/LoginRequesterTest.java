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

//	@Test(expected = NullPointerException.class)
//	public void testWhenItDoesntReceiveAnStrategyShouldRaiseNullPointer()
//	{
//		// given
//		ILoginStrategy strategy = null;
//
//		// when
//		new LoginRequester().requestLogin(new StubLoginCallback());
//
//		// then raise NullPointer
//	}

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

	private class StubStrategy implements ILoginStrategy{

		private boolean success;
		private String errorMessage;

		@Override
		public void doLogin(ILoginCallback callback) {
			if(success)
			{
				callback.onSuccess();
			}else
			{
				callback.onError(getErrorMessage());
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

	private class StubLoginCallback implements ILoginCallback{

		private boolean success;
		private String errorMessage;

		@Override
		public void onSuccess() {
			success = true;
		}

		@Override
		public void onError(String messageError) {
      this.errorMessage = messageError;
		}

		public boolean isSuccess()
		{
			return success;
		}

		public String getErrorMessage()
		{
			return errorMessage;
		}
	}
}

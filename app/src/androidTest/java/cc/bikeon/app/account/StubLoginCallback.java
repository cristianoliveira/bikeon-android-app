package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 07/05/15.
 */
class StubLoginCallback implements LoginCallback {

	private boolean isSuccess = false;
	private String errorMessage;

	@Override
	public void onLoginSuccess() {
		isSuccess = true;
	}

	@Override
	public void onLoginError(String messageError) {
		this.errorMessage = messageError;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

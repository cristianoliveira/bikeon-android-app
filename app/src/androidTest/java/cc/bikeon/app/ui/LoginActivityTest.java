package cc.bikeon.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityTestCase;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.view.WindowManager;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest extends ActivityUnitTestCase<LoginActivity> {

	Intent mLaunchIntent;
	LoginActivity loginActivity;

	public LoginActivityTest() {
		super(LoginActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// given
		mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), LoginActivity.class);
		startActivity(mLaunchIntent, null, null);
	}

	@SmallTest
	public void testOnLoginSuccessItShouldFinish()
	{
		// given
		loginActivity = (LoginActivity) getActivity();

		// when
		loginActivity.onLoginSuccess();

		// then
		assertTrue(this.isFinishCalled());
	}

	@SmallTest
	public void testOnLoginErrorItShouldShowAlertDialog()
	{
		// given
		loginActivity = (LoginActivity) getActivity();

		// when
		try {
			loginActivity.onLoginError("Error message");
			fail("OnLoginError should show dialog box");
		}catch (WindowManager.BadTokenException e)
		{ }

		assertFalse(this.isFinishCalled());
	}

}
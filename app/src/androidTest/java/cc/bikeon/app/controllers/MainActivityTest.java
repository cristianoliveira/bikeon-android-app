package cc.bikeon.app.controllers;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.widget.LoginButton;

/**
 * Created by cristian.rosa on 1/20/2015.
 */
public class MainActivityTest extends android.test.ActivityUnitTestCase<LoginActivity>
{

    LoginActivity mainActivity;
    ImageView logo;
    LoginButton facebookLoginBtn;

    public MainActivityTest()
    {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), LoginActivity.class);
        startActivity(mLaunchIntent, null, null);

        mainActivity = getActivity();
    }

    @SmallTest
    public void testViewItens()
    {
        logo = mainActivity.logo;
        assertNotNull(logo);
        facebookLoginBtn = mainActivity.facebookLoginBtn;
        assertNotNull(facebookLoginBtn);
    }

    @SmallTest
    public void testValidateStartPropertyBeforeAnimation()
    {
        int visibility = mainActivity.facebookLoginBtn.getVisibility();
        assertEquals(visibility, View.INVISIBLE);
        int top        = mainActivity.logo.getTop();
        assertEquals(top, 0);
    }

    @SmallTest
    public void testValidateStartPropertyAfterAnimation()
    {
        mainActivity.onResume();

        int visibility = mainActivity.facebookLoginBtn.getVisibility();
        assertEquals(visibility, View.VISIBLE);
        RelativeLayout.LayoutParams lp        =
                (RelativeLayout.LayoutParams)mainActivity.logo.getLayoutParams();
        assertEquals(lp.getRules()[RelativeLayout.ALIGN_TOP], mainActivity.logo.getId());
    }
}

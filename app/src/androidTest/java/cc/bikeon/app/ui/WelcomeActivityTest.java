package cc.bikeon.app.ui;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Created by cristianoliveira on 05/06/15.
 */
public class WelcomeActivityTest extends ActivityUnitTestCase<WelcomeActivity> {

    public WelcomeActivityTest() {
        super(WelcomeActivity.class);
    }

    @LargeTest
    public void testWelcomeActivityCreate()
    {
        // given
        Intent mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), WelcomeActivity.class);
        startActivity(mLaunchIntent, null, null);

        // when
        WelcomeActivity activity = getActivity();

        // then
        assertNotNull(activity);
    }
}

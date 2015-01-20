package cc.bikeon.app.tests.app.helpers;

import android.app.Application;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import cc.bikeon.app.BikeOnApplication;
import cc.bikeon.app.R;
import cc.bikeon.app.helpers.AppHelper;

/**
 * Created by cristian.rosa on 1/19/2015.
 */
public class AppHelperTest extends ApplicationTestCase<BikeOnApplication> {

    public AppHelperTest() {
        super(BikeOnApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
        AppHelper.init(getApplication());
    }

    @SmallTest
    public void testGetApplicationInstance()
    {
        BikeOnApplication app = (BikeOnApplication)AppHelper.getApplication();
        assertNotNull(app);
        assertEquals(this.getApplication(), app);
    }

    @SmallTest
    public void testGetResourceString()
    {
        String resString = AppHelper.resString(R.string.string_test_case);
        assertNotNull(resString);
        assertEquals(resString, "bikeon");
    }
}

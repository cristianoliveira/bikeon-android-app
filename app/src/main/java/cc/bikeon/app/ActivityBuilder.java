package cc.bikeon.app;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by cristian.rosa on 1/21/2015.
 *
 *  Class to handler the routes of application
 *
 */
public class ActivityBuilder {

    Activity currentActivity;
    Intent intent;

    ActivityBuilder(Activity context)
    {
        this.currentActivity = context;
    }

    public static ActivityBuilder from(Activity currentActivity)
    {
        return new ActivityBuilder(currentActivity);
    }

    public ActivityBuilder goTo(Class<?> toActivity)
    {
        intent = new Intent(currentActivity, toActivity);
        return this;
    }

    public Intent getIntent()
    {
        return intent;
    }

    public ActivityBuilder start()
    {
        currentActivity.startActivity(intent);
    }

    public ActivityBuilder startForResult(int returnCode)
    {
        currentActivity.startActivityForResult(intent, returnCode);
    }

    public void finishCurrent()
    {
        currentActivity.finish();
    }

}

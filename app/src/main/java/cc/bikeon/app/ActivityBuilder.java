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

    public static ActivityBuilder Builder(Activity currentActivity)
    {
        return new ActivityBuilder(currentActivity);
    }

    public ActivityBuilder activity(Class<?> toActivity)
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
        return this;
    }

    public ActivityBuilder startForResult(int returnCode)
    {
        currentActivity.startActivityForResult(intent, returnCode);
        return this;
    }

    public void finishCurrent()
    {
        currentActivity.finish();
    }

}

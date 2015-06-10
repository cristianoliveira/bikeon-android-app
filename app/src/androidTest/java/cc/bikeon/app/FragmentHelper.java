package cc.bikeon.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * Created by cristianoliveira on 04/06/15.
 */
public class FragmentHelper {

    /**
     *
     * Return current fragment active from activity
     *
     * @param activity Activity with fragments
     * @return current fragment active
     */
    public static Fragment getActiveFragment(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            return null;
        }
        String tag = manager.getBackStackEntryAt(getLastIndex(manager)).getName();
        return manager.findFragmentByTag(tag);
    }

    private static int getLastIndex(FragmentManager manager){
        return manager.getBackStackEntryCount() - 1;
    }
}

package cc.bikeon.app.account;

import android.app.Activity;

import cc.bikeon.app.account.requesters.BikeOnLoginRequester;
import cc.bikeon.app.account.requesters.FacebookLoginRequester;

/**
 * Responsible to choose the login requester.
 * Created by cristianoliveira on 29/08/15.
 */
public class LoginStrategyChooser {
    /**
     * Return Login strategy based on {@link LoginStrategy} given
     * @param strategy The LoginStrategy enum
     * @param activity Current Activity
     * @return The strategy for login (Default: {@link LoginStrategy#BIKEON} )
     */
    public LoginRequester get(LoginStrategy strategy, Activity activity) {

        if (strategy == LoginStrategy.FACEBOOK) {
            return FacebookLoginRequester.Factory.create(activity);
        }
        return new BikeOnLoginRequester();
    }

}

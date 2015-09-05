package cc.bikeon.app.account;

import android.content.Intent;

/**
 * Represent strategy of authentication.
 * Created by cristianoliveira on 04/05/15.
 */
public interface LoginRequester {

    void doLogin(LoginCallback callback);
    void onActivityResult(int requestCode, int resultCode, Intent data);

}

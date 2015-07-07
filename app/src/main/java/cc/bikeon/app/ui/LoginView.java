package cc.bikeon.app.ui;

import android.app.Activity;

/**
 * Created by cristianoliveira on 01/07/15.
 */
public interface LoginView {
    Activity getActivity();
    void showLoginError(String message);
    void gotoMainActivity();
}

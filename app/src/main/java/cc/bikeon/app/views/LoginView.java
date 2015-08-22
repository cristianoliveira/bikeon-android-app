package cc.bikeon.app.views;

import android.app.Activity;

/**
 * View interface for Login Presenter
 * Created by cristianoliveira on 01/07/15.
 */
public interface LoginView {
    Activity getActivity();
    void showLoginError(String message);
    void gotoMainActivity();
}

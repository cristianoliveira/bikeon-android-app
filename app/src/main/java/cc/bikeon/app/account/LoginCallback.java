package cc.bikeon.app.account;

import cc.bikeon.app.account.session.SessionAccount;

/**
 * Callback interface for login requests.
 * Created by cristianoliveira on 04/05/15.
 */
public interface LoginCallback {

    void onLoginSuccess(SessionAccount sessionAccount);

    void onCancel();

    void onLoginError(String messageError);
}

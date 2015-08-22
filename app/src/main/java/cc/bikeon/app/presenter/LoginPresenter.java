package cc.bikeon.app.presenter;

import cc.bikeon.app.R;
import cc.bikeon.app.account.FacebookLoginStrategy;
import cc.bikeon.app.account.FakeLoginStrategy;
import cc.bikeon.app.account.LoginCallback;
import cc.bikeon.app.account.LoginRequester;
import cc.bikeon.app.views.LoginView;

/**
 * Responsible to implement logic for login
 * <p/>
 * Created by cristianoliveira on 30/06/15.
 */
public class LoginPresenter implements LoginCallback {
    private LoginRequester loginRequester;
    private LoginView view;

    public LoginPresenter(LoginView view, LoginRequester loginRequester) {
        this.view = view;
        this.loginRequester = loginRequester;
        this.loginRequester.setCallback(this);
    }

    public void requestLogin(int viewrRequesterId) {
        switch (viewrRequesterId) {
            case R.id.btnFacebookLogin:
                loginRequester.setStrategy(new FacebookLoginStrategy(view.getActivity()));
                break;
            case 10: //TODO remove before release
                loginRequester.setStrategy(new FakeLoginStrategy());
                break;
        }

        loginRequester.requestLogin();
    }

    @Override
    public void onLoginSuccess(Object SessionType) {
        view.gotoMainActivity();
    }

    @Override
    public void onLoginError(String messageError) {
        view.showLoginError(messageError);
    }

}

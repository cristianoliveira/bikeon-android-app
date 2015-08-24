package cc.bikeon.app.views;

/**
 * View interface to present data.
 * Created by cristianoliveira on 23/08/15.
 */
public interface View {

    /**
     * Show error for a given String Resource Id
     * @param messageResId resource id of message;
     */
    void showError(int messageResId);

}

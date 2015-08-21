package cc.bikeon.app.account;

/**
 * Represent session on application
 * <p/>
 * Created by cristianoliveira on 29/06/15.
 */
public interface Session<T> {
    /**
     * Return data related with session
     *
     * @return T instance
     */
    T getData();

    /**
     * Session is active?
     *
     * @return boolean
     */
    boolean isActive();

    /**
     * Close this session and return status
     *
     * @return is Session closed?
     */
    boolean close();

}

package cc.bikeon.app.account;

/**
 * Created by cristianoliveira on 05/05/15.
 */
public interface IAccountSession {

	public boolean isSessionOpened();

	public void closeSession();

	public Object getSession();

}

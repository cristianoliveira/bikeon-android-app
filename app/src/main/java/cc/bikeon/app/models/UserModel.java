package cc.bikeon.app.models;

import java.util.Collection;

import cc.bikeon.app.domain.user.User;


/**
 * Created by cristianoliveira on 23/08/15.
 */
public class UserModel implements Model<User> {

    @Override
    public boolean save(User data) {
        return false;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}

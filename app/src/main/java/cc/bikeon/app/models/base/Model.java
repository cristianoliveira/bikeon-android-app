package cc.bikeon.app.models.base;

import java.util.Collection;

/**
 * Model to manage data
 *
 * @param <T> Type of data
 * Created by cristianoliveira on 23/08/15.
 */
public interface Model<T> {

    /**
     * Persist a given data
     * @param data
     * @return save success.
     */
    boolean save(T data);

    /**
     * Retrieve all values from model
     * @return Collection of data
     */
    Collection<T> getAll();
}

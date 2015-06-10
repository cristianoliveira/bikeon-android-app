package cc.bikeon.app.services.rest;

/**
 * Created by cristianoliveira on 06/06/15.
 */
public interface RestResponse<T> {

    /**
     *
     *  Must return the status of request
     *
     * @return String with status description
     */
    public String getStatus();

    /**
     *
     *  Method to request formatted data of rest request
     *
     * @return data
     */
    public T getData();

}

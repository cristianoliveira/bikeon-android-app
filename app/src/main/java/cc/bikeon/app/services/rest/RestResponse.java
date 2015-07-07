package cc.bikeon.app.services.rest;

import java.util.List;

import cc.bikeon.app.domain.Coordinate;

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
    public List<Coordinate> getData();

}

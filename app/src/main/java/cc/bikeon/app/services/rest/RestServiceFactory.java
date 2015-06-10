package cc.bikeon.app.services.rest;

/**
 * Created by cristianoliveira on 09/06/15.
 */
public class RestServiceFactory {

    /**
     *
     * Create a new instance of a given Service Interface
     *
     * @param apiServiceInterface Service interface
     * @param provider Provider for Web Service
     * @param <T>  Service interface type
     * @return new instance of Service
     */
    public static <T> T create(Class<T> apiServiceInterface, RestProvider provider){
        return new RestClient(provider).getService(apiServiceInterface);
    }
}

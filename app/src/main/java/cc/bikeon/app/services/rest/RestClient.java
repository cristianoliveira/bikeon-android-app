package cc.bikeon.app.services.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 *
 *  Allow to create Web Services requester
 *
 * Created by cristianoliveira on 12/05/15.
 */
public class RestClient {

    private RestProvider provider;
    private RestAdapter restAdapter;

    /**
     *
     *  Initialize Client providing a REST API provider
     *
     * @param provider REST API provider
     */
    public RestClient(RestProvider provider)
    {
        this.provider = provider;
    }

    /**
     *
     *  Register a Service with a given interface
     *
     * @param apiServiceInterface Service Interface
     * @param <T> Type of Service Interface
     * @return Service registered to use
     */
    public <T> T getService(Class<T> apiServiceInterface){
        return getRestAdapter().create(apiServiceInterface);
    }

    /**
     *
     *  Return a Adapter to registre Services.
     *  Adapts a Java interface to a REST API.
     *
     *  http://square.github.io/retrofit/javadoc/retrofit/RestAdapter.html
     *
     * @return RestAdapter
     */
    public RestAdapter getRestAdapter(){
        if(restAdapter == null){
            Gson gson = new GsonBuilder()
                            .registerTypeAdapterFactory(new JsonTypeAdapterFactory())
                            .create();

            restAdapter = new RestAdapter.Builder()
                                         .setLogLevel(RestAdapter.LogLevel.FULL)
                                         .setEndpoint(provider.getBaseUrl())
                                         .setConverter(new GsonConverter(gson))
                                         .build();
        }
        return restAdapter;
    }

}

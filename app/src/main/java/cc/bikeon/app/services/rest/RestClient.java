package cc.bikeon.app.services.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by cristianoliveira on 12/05/15.
 */
public class RestClient {

    IRestProvider provider;
    RestAdapter restAdapter;


    public RestClient(IRestProvider provider)
    {
        this.provider = provider;
    }

    public Object getService(Class apiServiceInterface)
    {
        return getRestAdapter().create(apiServiceInterface);
    }

    public RestAdapter getRestAdapter()
    {
        if(restAdapter == null)
        {
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

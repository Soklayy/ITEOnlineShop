package kh.edu.rupp.ite.onlineshop.api;

import kh.edu.rupp.ite.onlineshop.api.service.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private Service service;

    public ServiceProvider(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(Service.class);
    }

    public Service getService() {
        return service;
    }

}

package kh.edu.rupp.ite.onlineshop.api;

import kh.edu.rupp.ite.onlineshop.api.service.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {
    private Service service;

    public ServiceProvider(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(Service.class);
    }

    public Service getService() {
        return service;
    }

}

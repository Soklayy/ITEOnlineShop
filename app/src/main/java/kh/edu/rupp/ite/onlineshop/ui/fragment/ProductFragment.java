package kh.edu.rupp.ite.onlineshop.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.adapter.ProductAdapter;
import kh.edu.rupp.ite.onlineshop.api.ServiceProvider;
import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.api.service.Service;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater,container,false);
        getProduct();
        return binding.getRoot();

    }

    //getProductFromServer
    private void getProduct(){

        ServiceProvider serviceProvider = new ServiceProvider();
        Call<List<Product>> task = serviceProvider.getService().getProduct();

        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    showList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ProductFragment","Load product failed: "+t);
            }
        });
    }

    private void showList(List<Product> productList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.productList.setLayoutManager(linearLayoutManager);

        //adapter
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.submitList(productList);

        binding.productList.setAdapter(productAdapter);
    }
}

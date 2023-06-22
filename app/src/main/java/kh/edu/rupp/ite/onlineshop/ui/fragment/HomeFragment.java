package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.adapter.ProductAdapter;
import kh.edu.rupp.ite.onlineshop.api.ServiceProvider;
import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        getProduct();
        return binding.getRoot();
    }


    private void getProduct(){
        Call<List<Product>> task = new ServiceProvider().getService().getProduct();

        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    showHorizontalList(response.body());
                    showVerticalList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ProductFragment","Load product failed ",t);
            }
        });
    }

    private void showVerticalList(List<Product> productList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.verticalList.setLayoutManager(linearLayoutManager);

        //adapter
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.submitList(productList);

        binding.verticalList.setAdapter(productAdapter);
    }

    private void showHorizontalList(List<Product> productList){
        //layout management
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.horizontalList.setLayoutManager(linearLayoutManager);

        //adapter
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.submitList(productList);

        binding.horizontalList.setAdapter(productAdapter);
    }
}

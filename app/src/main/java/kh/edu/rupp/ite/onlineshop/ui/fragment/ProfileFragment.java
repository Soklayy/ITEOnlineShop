package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.squareup.picasso.Picasso;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.ServiceProvider;
import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.Service;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        getProfile();
        return binding.getRoot();
    }

//    get profile from server
    private void getProfile(){

        ServiceProvider serviceProvider = new ServiceProvider();

        Call<Profile> task = serviceProvider.getService().getProfile();

        task.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    showProfile(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e("ProductFragment","Load profile failed ",t);
            }
        });
    }

    private void showProfile(Profile profile){
        Picasso.get().load(profile.getImageUrl()).into(binding.profileImage);
        binding.profileName.setText(profile.getFirsName()+" "+profile.getLastName());
        binding.email.setText(profile.getEmail());
        binding.emailMini.setText(profile.getEmail());
        binding.phoneNumber.setText(profile.getPhoneNumber());
        binding.birthDay.setText(profile.getBirthday());
        binding.address.setText(profile.getAddress());
    }
}
